package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pix3 extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnConfirmar;
    private TextView txt_saldo, txt_saldototal, txt_cpf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix3);

        //Localizando variaveis no código
        txt_saldo = findViewById(R.id.tvValorFinalNumero);
        txt_saldototal = findViewById(R.id.tvSaldoConta);
        txt_cpf = findViewById(R.id.tvCpfNumero);

        //Definindo Saldo como o correspondente na classe Pessoa
        txt_saldo.setText(String.valueOf(Pessoa.getSaldodigitado()));
        txt_saldototal.setText(String.valueOf(Pessoa.getSaldo()));
        txt_cpf.setText(String.valueOf(Pessoa.getCpf()));

        btnCancelar = findViewById(R.id.btnCancelarPix);
        btnCancelar.setOnClickListener(view -> {
            Intent intentHome = new Intent(Pix3.this, Pix.class);
            startActivity(intentHome);
            finish();
        });

        btnConfirmar = findViewById(R.id.btnConfirmarPix);
        btnConfirmar.setOnClickListener(view -> {
            Intent intentPix4 = new Intent(Pix3.this, Pix4.class);
            startActivity(intentPix4);
            finish();
        });
    }
}