package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class solicitar_portabilidade extends AppCompatActivity {

   ImageButton ib_voltar_portabilidade_pix;
   Button btn_salvar_portabilidade_pix_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_portabilidade);


        ib_voltar_portabilidade_pix = findViewById(R.id.ib_voltar_portabilidade_pix);
        ib_voltar_portabilidade_pix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),Configurar_Pix.class);
            finish();
            startActivity(intent);
        });

        btn_salvar_portabilidade_pix_2 = findViewById(R.id.btn_salvar_portabilidade_pix_2);
        btn_salvar_portabilidade_pix_2.setOnClickListener(v -> Toast.makeText(solicitar_portabilidade.this,"Solocitação efetuada com sucesso! em breve o banco entrara em contato.",Toast.LENGTH_SHORT).show());

    }
}