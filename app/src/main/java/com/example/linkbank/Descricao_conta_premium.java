package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class Descricao_conta_premium extends AppCompatActivity {

    ImageView seta_voltar;
    AppCompatButton btn_avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_conta_premium);

        seta_voltar = findViewById(R.id.seta_voltar);
        seta_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChave = new Intent(getApplicationContext(), Cartao.class);
                finish();
                startActivity(intentChave);
            }
        });

        btn_avancar = findViewById(R.id.btn_avancar);
        btn_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChave = new Intent(getApplicationContext(), Solicitar_mudar_tipo_conta_premium.class);
                finish();
                startActivity(intentChave);
            }
        });
    }
}