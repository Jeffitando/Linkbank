package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class Seguros_menu extends AppCompatActivity {

    Button btn_simular2;
    ImageButton  ib_voltar_seguros_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguros_menu);

        getWindow().setStatusBarColor(ContextCompat.getColor(Seguros_menu.this, R.color.barra));

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btn_simular2 = findViewById(R.id.btn_simular2);
        btn_simular2.setOnClickListener(view -> {
            Intent intentConfig = new Intent(getApplicationContext(), Seguros_simulacao.class);
            finish();
            startActivity(intentConfig);
        });


        ib_voltar_seguros_menu = findViewById(R.id.ib_voltar_seguros_menu);
        ib_voltar_seguros_menu.setOnClickListener(v -> {
            Intent intentVoltarMenuSeguros = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intentVoltarMenuSeguros);
        });
    }
}

