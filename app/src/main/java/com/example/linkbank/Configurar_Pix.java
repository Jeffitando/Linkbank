package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Configurar_Pix extends AppCompatActivity {

    private TextView tv_minhas_chaves_pix, textView36, textView38, extratoPix;
    private ImageButton ib_voltar_configPix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_pix);

        tv_minhas_chaves_pix = findViewById(R.id.txtv_minhas_chaves_pix);
        ib_voltar_configPix = findViewById(R.id.ib_voltar_configPix);

        tv_minhas_chaves_pix.setOnClickListener(view -> {
            Intent intentChavesPix = new Intent(getApplicationContext(), Cadastrar_Pix.class);
            startActivity(intentChavesPix);
        });

        ib_voltar_configPix.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intent);

        });

        extratoPix = findViewById(R.id.textView40);
        extratoPix.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExtratoPix.class);
            finish();
            startActivity(intent);
        });

        textView36 = findViewById(R.id.textView36);
        textView36.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplicationContext(), solicitar_portabilidade.class);
            finish();
            startActivity(intent2);
        });

        textView38 = findViewById(R.id.textView38);
        textView38.setOnClickListener(v -> {
            Intent intent3 = new Intent(getApplicationContext(), limites_pix.class);
            finish();
            startActivity(intent3);
        });

    }
}