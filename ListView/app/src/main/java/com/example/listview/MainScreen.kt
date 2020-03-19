package com.example.listview

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.fila.view.*
import java.util.*
import kotlin.collections.ArrayList

public class MainScreen : AppCompatActivity()
{
    var tokens = ArrayList<Token>()
    var ml:MyList?=null
    var selected:Token?=null;

    //NOTIFICACIONES
    var notificationManager: NotificationManager?=null;
    var notificationChannel: NotificationChannel?=null;
    var notification: Notification?=null;

    var lastCheck:Calendar?=null;
    var currentDate:Calendar = Calendar.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        //AGREGAR TOKENS
        tokens.add(Token("Como afecta la crianza en el desarrollo mental de nuestros hijos",
            "Crianza severa puede alterar el desarrollo cerebral",
            R.drawable.imagen1,
            "Una baja sensibilidad parental y una crianza severa y negativa pueden afectar el\n" +
                    "desarrollo cerebral del niño en la forma en cómo organiza su conducta.\n",
            "¿Qué significa que una crianza severa pueda alterar el desarrollo cerebral? El\n" +
                    "desarrollo de las habilidades cognitivas complejas (funciones ejecutivas cerebrales) comienza\n" +
                    "dentro de los primero 5 años del niño. En paralelo, se desarrolla la corteza pre frontal del\n" +
                    "cerebro, la cual orienta la forma de organizar la propia conducta en el niño ¿Suena importante\n" +
                    "no?. Estudios han demostrado que la experiencia ambiental, como por ejemplo la parentalidad,\n" +
                    "influye en el desarrollo de esta corteza (Bumm!) y claro dentro de los primeros años del niño,\n" +
                    "éste depende única y exclusivamente de sus cuidadores. Siendo la sensibilidad del cuidador, uno\n" +
                    "de los elementos centrales de la parentalidad, definida como la capacidad de interpretar de\n" +
                    "manera adecuada las señales del niño y responder a ellas de manera atingente.\n" +
                    "Entonces…¿Qué elementos afectan el funcionamiento cerebral del niño? Un estudio señala que\n" +
                    "la baja sensibilidad parental y una crianza severa (actos coercitivos y expresiones emocionales\n" +
                    "negativas dirigidas a los niños) están relacionadas con bajos puntajes obtenidos por el niño en\n" +
                    "la habilidad de entender la manera en que piensa y aprende (meta cognición) y controlar las\n" +
                    "respuestas impulsivas y generar respuestas mediadas por la atención y el razonamiento (auto\n" +
                    "control inhibitorio).",
            "Referencia: Lucassen, N., Kok, R., Bakermans‐Kranenburg, M. J., Van Ijzendoorn, M. H., Jaddoe,\n" +
                    "V. W., Hofman, A., ... & Tiemeier, H. (2015). Executive functions in early childhood: The role of\n" +
                    "maternal and paternal parenting practices. British Journal of Developmental Psychology, 33(4),\n" +
                    "489-505.",
            1));
        tokens.add(Token("Heredando al intelligencia emocional",
            "Competencia Emocional",
                        R.drawable.imagen2,
          "¿Por qué es importante incorporar el lenguaje emocional? La actitud y respuesta que el\n" +
                    "cuidador tiene de las emociones del niño juega un rol importante en la manera de enseñar sobre\n" +
                    "las emociones y al mismo tiempo influencia la forma en cómo el niño expresa sus propias\n" +
                    "emociones.",
            " Diversos estudios han demostrado que los vínculos y experiencias que tienen niños\n" +
                    "y niñas con sus familias, cuidadores, profesores y referentes cercanos, tienen influencia directa\n" +
                    "en su expresión y regulación emocional. Donde se ha revisado que aquellos niños y niñas que\n" +
                    "reciben un modelado positivo sobre expresión y manejo de emociones de sus figuras parentales,\n" +
                    "también adquieren mayor manejo y expresión apropiada de sus emociones. Además aquellas\n" +
                    "figuras parentales que presentan dificultades para identificar sus emociones (ira, tristeza)\n" +
                    "pueden tener mayores dificultades para detectar las emociones de sus hijos e hijas.\n",
            "Referencia: Havighurst, S. S., Wilson, K. R., Harley, A. E., & Prior, M. R. (2009). Tuning in to kids:\n" +
                    "an emotion‐focused parenting program—initial findings from a community trial. Journal of\n" +
                    "Community Psychology, 37(8), 1008-1023.",2));
        tokens.add(Token(" Estrés en los niños y funciones de la parentalidad",
                        " Trauma infantil",
                        R.drawable.imagen3,
            "Sin descripción",
            " Los cuidadores pueden modular las respuestas al estrés en los niños a través de la\n" +
                    "seguridad del vínculo en la relación que posean. Lo que permite controlar la respuesta fisiológica\n" +
                    "y comportamental del niño. Siendo este apoyo uno de los pilares del desarrollo de la regulación\n" +
                    "emocional. ¿A través de qué? El vínculo de apego seguro es identificado como un mediador en\n" +
                    "las respuestas al estrés. El poder de las relaciones de apego seguras sirve para amortiguar o\n" +
                    "prevenir aumentos en el nivel de cortisol del niño.\n" +
                    "Las dinámicas familiares también influencian el desarrollo de la reactividad del cortisol en niños.\n" +
                    "En estudios de observación en ambientes naturales en niños de 2 meses a 17 años arrojan\n" +
                    "evidencia de que los eventos familiares traumáticos (conflicto, castigo, vergüenza, peleas graves\n" +
                    "y peleas) están fuertemente asociados con períodos de niveles elevados de cortisol, en\n" +
                    "comparación a sus propios niveles en días menos caóticos en la familia (Flinn e England 1995). ",
                    "Gunnar, M., & Quevedo, K. (2007). The neurobiology of stress and development. Annu. Rev.\n" +
                            "Psychol., 58, 145-173",3));
        tokens.add(Token(" Las respuestas al trauma infantil son mediadas por el nivel de desarrollo y apoyo parental",
                "Trauma infantil",
                    R.drawable.imagen4,
                "Sin descripción",
            "Después de experimentar estos hechos traumáticos muchos niños son resilientes y no\n" +
                    "generan síntomas de trauma.\n" +
                    "\uF0D8 Niños menores dependen más de las reacciones de los padres al trauma que niños\n" +
                    "mayores.\n" +
                    "\uF0D8 Si los padres reaccionan bien, los niños menores no desarrollan necesariamente\n" +
                    "síntomas traumáticos a largo plazo.\n" +
                    "\uF0D8 Si el trauma se vivió muy tempranamente, puede alterar dramáticamente la trayectoria\n" +
                    "del desarrollo, más que haber vivido trauma crónico en la adolescencia.",
                "Referencia: Schnyder, U., & Cloitre, M. (Eds.). (2015). Evidence based treatments for traumarelated psychological disorders: A practical guide for clinicians. Springer.",
                4));
        for(i in 0..16)
        {
            tokens.add(Token("Lorem ipsum dolor sit amet",
                    "Lorem ipsum dolor sit amet",
                R.drawable.logotipo,
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",i+4))
        }

        ml = MyList(this,tokens);
        listViewXML.adapter = ml;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager;
            CreateChannel();
            CreateNotification("Come back!","You've been away for too long");
        }

        
    }

    fun setAlarm()
    {
        var intent = Intent(applicationContext,Notification_receiver::class.java);
        var pending = PendingIntent.getBroadcast(applicationContext,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        currentDate.set(Calendar.HOUR_OF_DAY,lastCheck!!.get(Calendar.HOUR_OF_DAY))
        currentDate.set(Calendar.MINUTE,lastCheck!!.get(Calendar.HOUR_OF_DAY))
        currentDate.set(Calendar.SECOND,lastCheck!!.get(Calendar.HOUR_OF_DAY))

        var alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, currentDate.timeInMillis,AlarmManager.INTERVAL_DAY,pending);
    }

    override fun onStart() {
        super.onStart()
        notificationManager!!.cancel(1);
    }

    override fun onResume() {
        super.onResume()
        notificationManager!!.cancel(1);
    }

    override fun onPause() {
        super.onPause()
        lastCheck = Calendar.getInstance();
        setAlarm();
    }

    override fun onStop() {
        super.onStop()
        lastCheck = Calendar.getInstance();
        setAlarm();
    }

    override fun onDestroy() {
        super.onDestroy()
        lastCheck = Calendar.getInstance();
        setAlarm();
    }

    fun CreateChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channelName = getString(R.string.channel_name);
            notificationChannel =
                NotificationChannel(channelName, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel!!.description = getString(R.string.channel_description);
            notificationChannel!!.setSound(
                RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND),
                null
            );
            notificationChannel!!.vibrationPattern = LongArray(4, { 250;250;250;250 });
            notificationChannel!!.enableVibration(true);

            notificationManager!!.createNotificationChannel((notificationChannel!!));
        }
    }

    fun CreateNotification(title:String,msg:String)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val myIntent = Intent(this, this::class.java);
            val pIntent = PendingIntent.getActivity(this, 0, myIntent, 0);
            notification = Notification.Builder(applicationContext, getString(R.string.channel_name))
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pIntent)
                .setSound(RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND))
                .setAutoCancel(true)
                .build();


            notification!!.defaults = Notification.DEFAULT_ALL;
            notification!!.audioStreamType = AudioManager.STREAM_ALARM;
        }
    }

    fun RingNotification()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationManager!!.notify(1,notification);
        }
    }

    public inner class MyList:BaseAdapter{

        var tokens = ArrayList<Token>()
        var context:Context?=null

        constructor(context:Context, tokens:ArrayList<Token>):super(){

            this.tokens = tokens
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            var myView:View?=null
            val token = this.tokens[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            myView = inflater.inflate(R.layout.fila, null);

            myView.setOnClickListener()
            {
                selected = token;
                val intent = Intent(applicationContext,TokenDisplay::class.java).apply {
                    putExtra("character",token);
                }
                startActivity(intent);
            }

            myView.name.text = token.name;
            myView.icon.setImageResource(token.resID!!);
            myView.number.text = token.number.toString() + "/" + tokens.size.toString();
            myView.themeTxt.text = "Theme: " + token.theme;
            myView.description.text = token.description;

            return myView
        }

        override fun getItem(position: Int): Any {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this.tokens[position]
        }

        override fun getItemId(position: Int): Long {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            return position.toLong()
        }

        override fun getCount(): Int {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this.tokens.size
        }
    }

    public inner class Notification_receiver: BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?)
        {
            RingNotification();
        }
    }

}
