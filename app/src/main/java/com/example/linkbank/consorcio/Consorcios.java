package com.example.linkbank.consorcio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.linkbank.Gera_menu2;
import com.example.linkbank.R;

public class Consorcios extends AppCompatActivity {

    private ImageButton ib_voltar_consorcios;
    private Button btnCarro;
    private Button btnMoto;
    private Button btnCasa;
    private Button btnTerreno;
    private Button btnReforma;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consorcios);

        ib_voltar_consorcios = findViewById(R.id.ib_voltar_consorcios);
        ib_voltar_consorcios.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intent);

        });

        btnCarro = findViewById(R.id.btnCarro);
        btnCarro.setOnClickListener(view -> {
            Intent intentCarro = new Intent(getApplicationContext(), ConsorcioDeCarro.class);
            startActivity(intentCarro);

        });

        btnMoto = findViewById(R.id.btnMoto);
        btnMoto.setOnClickListener(view -> {
            Intent intentMoto = new Intent(getApplicationContext(), ConsorcioDeMoto.class);
            startActivity(intentMoto);

        });

        btnCasa= findViewById(R.id.btnCasa);
        btnCasa.setOnClickListener(view -> {
            Intent intentCasa = new Intent(getApplicationContext(), ConsorcioDeCasa.class);
            startActivity(intentCasa);

        });

        btnTerreno= findViewById(R.id.btnTerreno);
        btnTerreno.setOnClickListener(view -> {
            Intent intentTerreno = new Intent(getApplicationContext(), ConsorcioDeTerreno.class);
            startActivity(intentTerreno);

        });

        btnReforma = findViewById(R.id.btnReforma);
        btnReforma.setOnClickListener(view -> {
            Intent intentReforma = new Intent(getApplicationContext(), ConsorcioDeReforma.class);
            startActivity(intentReforma);

        });



    }
}