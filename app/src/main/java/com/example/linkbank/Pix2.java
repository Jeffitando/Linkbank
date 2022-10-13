package com.example.linkbank;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Pix2 extends AppCompatActivity {

    private TextView txt_saldoEscrito,txt_saldo, txt_cpf;
    private Button btnAvancar;
    private ImageView txt_voltar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix2);

        //Localizando as variaveis no código
        txt_saldo = findViewById(R.id.tvSaldoConta);
        txt_saldoEscrito = findViewById(R.id.etValorPix);
        txt_cpf = findViewById(R.id.edtCpf);
        btnAvancar = findViewById(R.id.btnAvancar);

        txt_saldo.setText(String.valueOf(Pessoa.getSaldo()));
        txt_cpf.setText(String.valueOf(Pessoa.getCpf()));

        reduzindoSaldo_mudaTela();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void reduzindoSaldo_mudaTela(){

        btnAvancar.setOnClickListener(view -> {

            String saldoString = txt_saldoEscrito.getText().toString(); //Recuperando valor valor digitado
            int saldoRecuperado = Integer.parseInt(saldoString);


            float resultado = Pessoa.getSaldo() - saldoRecuperado; //Fazendo calculo saldo - valor digitado

            Pessoa.setSaldo(resultado); //Setando novo saldo pós transferencia
            Pessoa.setSaldodigitado(saldoRecuperado);


            Intent intentPix3 = new Intent(Pix2.this, Pix3.class);
            startActivity(intentPix3);
            finish();
        });
        txt_voltar = findViewById(R.id.btn_voltar);

        txt_voltar.setOnClickListener(view -> {
            Intent intentPix2 = new Intent(Pix2.this, Pix.class);
            startActivity(intentPix2);
            finish();
        });
    }
}