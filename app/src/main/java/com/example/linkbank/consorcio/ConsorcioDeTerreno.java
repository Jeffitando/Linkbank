package com.example.linkbank.consorcio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.linkbank.R;

public class ConsorcioDeTerreno extends AppCompatActivity {

    private Button btnSimular;
    private ImageButton ib_voltar_consorcios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consorcio_de_terreno);

        ib_voltar_consorcios = findViewById(R.id.ib_voltar_consorcios);
        ib_voltar_consorcios.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Consorcios.class);
            finish();
            startActivity(intent);
        });

        btnSimular = findViewById(R.id.btnSimular);
        btnSimular.setOnClickListener(v -> {
            Intent intentSimular = new Intent(getApplicationContext(),ConsorcioAnalise.class);
            startActivity(intentSimular);
        });

    }
}