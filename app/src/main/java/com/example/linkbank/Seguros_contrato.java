package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Seguros_contrato extends AppCompatActivity {

   private Button btn_recalcular;
   private Button btn_contratar_seguro;
   private ImageButton ib_voltar_seguros_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguros_contrato);


        btn_recalcular = findViewById(R.id.btn_recalcular);
        btn_recalcular.setOnClickListener(view -> {
            Intent intentConfig = new Intent(getApplicationContext(),Seguros_simulacao.class);
            finish();
            startActivity(intentConfig);
        });

        btn_contratar_seguro = findViewById(R.id.btn_contratar_seguro);
        btn_contratar_seguro.setOnClickListener(view -> {
            Intent intentContratarSeguro = new Intent(getApplicationContext(), comprovante_contrato_seguro.class);
            finish();
            startActivity(intentContratarSeguro);
        });

        ib_voltar_seguros_menu = findViewById(R.id.ib_voltar_seguros_menu);
        ib_voltar_seguros_menu.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Seguros_simulacao.class);
            finish();
            startActivity(intent);
        });


    }


}
