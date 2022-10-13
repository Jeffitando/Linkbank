package com.example.linkbank;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class PoupancaExtrato extends AppCompatActivity {

    ImageButton ib_voltar_extrato;



    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupanca_extrato);

        ib_voltar_extrato = findViewById(R.id.ib_voltar_extrato);
        ib_voltar_extrato.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Conta_poupanca.class);
            finish();
            startActivity(intent);
        });




    }
}