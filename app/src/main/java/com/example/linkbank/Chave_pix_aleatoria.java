package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Chave_pix_aleatoria extends AppCompatActivity {

    private Button avancar_pix;
    private ImageButton ib_voltarPix;
    private ImageButton duvida_pix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chave_pix_aleatoria);

        duvida_pix = findViewById(R.id.duvida_pix);
        duvida_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDuvida = new Intent(getApplicationContext(), com.example.linkbank.duvida_pix.class);
                finish();
                startActivity(intentDuvida);
            }
        });


        ib_voltarPix = findViewById(R.id.ib_voltarPix);
        ib_voltarPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), pix_menu.class);
                finish();
                startActivity(intentVoltar);
            }
        });


        avancar_pix = findViewById(R.id.avancar_pix);
        avancar_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Recibo_pix_telefone.class);
                finish();
                startActivity(intent);
            }
        });

    }
}

