package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pix4 extends AppCompatActivity {

    private Button btnContinuar;
    private TextView txt_saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix4);

        //Localizando variaveis no código
        txt_saldo = findViewById(R.id.tvValorFinalNumero);


        //Definindo Saldo como o correspondente na classe Pessoa
        txt_saldo.setText(String.valueOf(Pessoa.getSaldodigitado()));

        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(view -> {
            Intent intentHome = new Intent(Pix4.this, Home.class);
            startActivity(intentHome);
        });
    }
}