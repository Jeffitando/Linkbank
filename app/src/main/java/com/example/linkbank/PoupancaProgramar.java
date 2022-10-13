package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PoupancaProgramar extends AppCompatActivity {

    ImageButton ib_voltar_programar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupanca_programar);

        ib_voltar_programar = findViewById(R.id.ib_voltar_programar);
        ib_voltar_programar.setOnClickListener(v -> {
            Intent intentProgramarVoltar = new Intent(getApplicationContext(), Conta_poupanca.class);
            finish();
            startActivity(intentProgramarVoltar);
        });
    }
}