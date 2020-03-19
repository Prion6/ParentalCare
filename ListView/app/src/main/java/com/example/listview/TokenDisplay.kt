package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_token_display.*
import kotlinx.android.synthetic.main.fila.*
import kotlinx.android.synthetic.main.fila.description
import kotlinx.android.synthetic.main.fila.icon
import kotlinx.android.synthetic.main.fila.name
import kotlinx.android.synthetic.main.fila.number
import kotlinx.android.synthetic.main.fila.themeTxt

class TokenDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_token_display)

        val token = intent.getSerializableExtra("character") as Token;

        icon.setImageResource(token.resID!!);
        name.setText(token.name!!);
        themeTxt.setText(token.theme!!);
        number.setText(token.number.toString());
        description.setText(token.description);
        message.setText(token.message + "\n\n" + token.sources);
    }
}
