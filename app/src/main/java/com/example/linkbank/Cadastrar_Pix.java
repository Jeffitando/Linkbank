package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Cadastrar_Pix extends AppCompatActivity {

    private Button btn_salvar_portabilidade_pix;
    private ImageButton ib_voltar_config_pix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pix);

        btn_salvar_portabilidade_pix = findViewById(R.id.btn_salvar_portabilidade_pix);
        btn_salvar_portabilidade_pix.setOnClickListener(v -> {
            String chaveHome = "pixMenu";

            Intent intent = new Intent(getApplicationContext(), ChavesPixAtivas.class);
            intent.putExtra("pixAct", chaveHome);
            startActivity(intent);
        });


        ib_voltar_config_pix = findViewById(R.id.ib_voltar_config_pix);
        ib_voltar_config_pix.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplicationContext(), Configurar_Pix.class);
            finish();
            startActivity(intent2);
        });

    }
}