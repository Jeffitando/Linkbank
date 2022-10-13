package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class Solicitar_mudar_tipo_conta_super extends AppCompatActivity {

    ImageView seta_voltar;
    AppCompatButton btn_solicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_mudar_tipo_conta_super);

        seta_voltar = findViewById(R.id.seta_voltar);
        seta_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChave = new Intent(getApplicationContext(), Cartao.class);
                finish();
                startActivity(intentChave);
            }
        });

        btn_solicitar = findViewById(R.id.btn_solicitar);
        btn_solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChave = new Intent(getApplicationContext(), Solicitacao_enviada.class);
                finish();
                startActivity(intentChave);
            }
        });
    }
}