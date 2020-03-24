package com.example.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity()
{
    var mAuth:FirebaseAuth?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance();

        mAuth!!.signOut();

        singInBtn.setOnClickListener()
        {
            CheckUser(userField.text.toString().trim().toLowerCase(),passwordField.text.toString().trim().toLowerCase())
            if(user != null)
            {
                Toast.makeText(this,"Authentication Success",Toast.LENGTH_LONG).show();
                val intent = Intent(applicationContext,MainScreen::class.java);
                startActivity(intent);
            }
        }

        registerBtn.setOnClickListener()
        {
            CreateUser(userField.text.toString().trim().toLowerCase(),passwordField.text.toString().trim().toLowerCase())
            if(user != null)
            {
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
            }
        }

    }

    companion object
    {
        var user:FirebaseUser?=null;
    }


    public override fun onStart() {
        super.onStart()
        MainActivity.user = mAuth!!.getCurrentUser()
    }

    fun CreateUser(email:String,password:String)
    {
        Toast.makeText(
            this@MainActivity,
            email + " - " + password,
            Toast.LENGTH_SHORT
        ).show()
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this
            ) { task ->
                if (!task.isSuccessful) {
                    val e = task.exception as FirebaseAuthException
                    Toast.makeText(
                        this@MainActivity,
                        "Failed Registration: " + e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else
                {
                    user = mAuth!!.getCurrentUser()
                }

                // ...
            }
    }

    fun CheckUser(email:String,password:String)
    {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this
            ) { task ->
                if (!task.isSuccessful) {

                }
                else
                {
                    user = mAuth!!.getCurrentUser()
                }
            }
    }


}
