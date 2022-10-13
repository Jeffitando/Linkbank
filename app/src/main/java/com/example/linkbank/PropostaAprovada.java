package com.example.linkbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PropostaAprovada extends AppCompatActivity {

    private ImageView btnProx;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta_aprovada);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnProx = findViewById(R.id.btnProx);

        btnProx.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("chave_proposta", "false");
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), EscolhaCartaoCadastro.class);
            finish();
            startActivity(intent);
        });

    }
}