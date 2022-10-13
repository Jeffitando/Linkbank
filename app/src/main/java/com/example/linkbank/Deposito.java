package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Deposito extends AppCompatActivity {

    private ImageButton ib_voltar_deposito;
    private Button avancar_deposito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        avancar_deposito = findViewById(R.id.avancar_deposito);
        avancar_deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Valor_deposito.class);
                finish();
                startActivity(intent);
            }
        });

        ib_voltar_deposito = findViewById(R.id.ib_voltar_deposito);
        ib_voltar_deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intent);
            }
        });


    }
}