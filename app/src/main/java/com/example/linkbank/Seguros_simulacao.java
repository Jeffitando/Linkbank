package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Seguros_simulacao extends AppCompatActivity {

    private Button btnSimular;
    private ImageButton ib_voltar_seguros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguros_simulacao);

        btnSimular = findViewById(R.id.buttonSimular);
        btnSimular.setOnClickListener(view -> {
            Intent intentSimular = new Intent(getApplicationContext(), Seguros_contrato.class);
            startActivity(intentSimular);
        });

        ib_voltar_seguros = findViewById(R.id.ib_voltar_seguros_menu);
        ib_voltar_seguros.setOnClickListener(view -> {
            Intent intentSeguros2 = new Intent(getApplicationContext(), Seguros_menu.class);
            startActivity(intentSeguros2);
        });
    }
}