package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class recuperar_senha_sms extends AppCompatActivity {

    ImageButton ib_voltar_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha_sms);



        ib_voltar_login = findViewById(R.id.ib_voltar_login);
        ib_voltar_login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),TelaDeEntrada.class);
            finish();
            startActivity(intent);
        });

    }
}