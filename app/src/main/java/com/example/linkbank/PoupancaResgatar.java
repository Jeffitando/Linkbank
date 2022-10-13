package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PoupancaResgatar extends AppCompatActivity {

    ImageButton ib_voltar_resgatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupanca_resgatar);

        ib_voltar_resgatar = findViewById(R.id.ib_voltar_resgatar);
        ib_voltar_resgatar.setOnClickListener(v -> {
            Intent intentResgatarVoltar = new Intent(getApplicationContext(), Conta_poupanca.class);
            finish();
            startActivity(intentResgatarVoltar);
        });
    }
}