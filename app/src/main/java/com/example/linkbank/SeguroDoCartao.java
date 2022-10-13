package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SeguroDoCartao extends AppCompatActivity {

    ImageButton ib_voltar_seguros_do_cartao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguro_do_cartao);

        ib_voltar_seguros_do_cartao = findViewById(R.id.ib_voltar_seguros_do_cartao);
        ib_voltar_seguros_do_cartao.setOnClickListener(view -> {
            Intent intentVoltarSeguroCartao = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentVoltarSeguroCartao);

        });

    }
}