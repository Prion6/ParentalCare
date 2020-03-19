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
            CheckUser(userField.text.toString(),passwordField.text.toString())
            if(user != null)
            {
                Toast.makeText(this,"Authentication Success",Toast.LENGTH_LONG).show();
                val intent = Intent(applicationContext,MainScreen::class.java);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Authentication Failed",Toast.LENGTH_LONG).show()
            }
        }

        registerBtn.setOnClickListener()
        {
            CreateUser(userField.text.toString(),passwordField.text.toString())
            if(user != null)
            {
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
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

    fun CreateUser(email:String,password:String): Boolean
    {
        var bool = false;
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(this
            ) {
                bool = true;
                MainActivity.user = mAuth!!.getCurrentUser();
            }
        return bool;
    }

    fun CheckUser(email:String,password:String): Boolean
    {
        var bool = false;
        mAuth!!.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener(this
        ) {
            bool = true;
            MainActivity.user = mAuth!!.getCurrentUser();
        }
        return bool;
    }


}
