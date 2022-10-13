package com.example.linkbank;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class PropostaEnviada extends AppCompatActivity {


    private ImageView duvida_proposta, imgSair;
    private Button btnSair;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta_enviada);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btnSair = findViewById(R.id.sairApp);
        btnSair.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("chave_proposta", "true");
            editor.commit();




            finish();

        });

        duvida_proposta = findViewById(R.id.duvida_proposta);
        duvida_proposta.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), analise_proposta.class);
            finish();
            startActivity(intent);
        });


    }
}