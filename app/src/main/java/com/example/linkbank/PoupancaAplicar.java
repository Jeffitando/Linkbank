package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PoupancaAplicar extends AppCompatActivity {

    ImageButton ib_voltar_aplicar;
    Button btnAplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupanca_aplicar);

        ib_voltar_aplicar = findViewById(R.id.ib_voltar_aplicar);
        ib_voltar_aplicar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Conta_poupanca.class);
            finish();
            startActivity(intent);
        });

    }
}