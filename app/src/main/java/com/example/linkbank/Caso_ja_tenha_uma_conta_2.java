package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Caso_ja_tenha_uma_conta_2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_ja_tenha_uma_conta2);

        Button btnEntrar = findViewById(R.id.entrarlogin);

        btnEntrar.setOnClickListener(view -> {

            Intent intentMainAcitivity = new Intent(getApplicationContext(), TermosEPrivacidade.class);

            startActivity(intentMainAcitivity);

        });
    }
}