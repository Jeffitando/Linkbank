package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class Solicitacao_enviada extends AppCompatActivity {

    AppCompatButton btn_home;
    ImageView seta_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao_enviada);

        seta_voltar = findViewById(R.id.seta_voltar);
        seta_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChave = new Intent(getApplicationContext(), Cartao.class);
                finish();
                startActivity(intentChave);
            }
        });

        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChave = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentChave);
            }
        });
    }
}