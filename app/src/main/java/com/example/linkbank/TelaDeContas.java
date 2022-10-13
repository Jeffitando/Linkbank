package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TelaDeContas extends AppCompatActivity {

    private ImageButton config, ib_voltar_seguros_menu;
    private ConstraintLayout layout_contaCorrente;
    private ConstraintLayout Poupanca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_contas);


        Poupanca  = findViewById(R.id.Poupanca);
        Poupanca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPoupanca = new Intent(getApplicationContext(), Conta_poupanca.class);
                finish();
                startActivity(intentPoupanca);
            }
        });



        layout_contaCorrente = findViewById(R.id.layout_contaCorrente);
        layout_contaCorrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentConta = new Intent(getApplicationContext(), Gera_menu2.class);
                finish();
                startActivity(intentConta);
            }
        });

        ib_voltar_seguros_menu = findViewById(R.id.ib_voltar_seguros_menu);
        ib_voltar_seguros_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
                finish();
                startActivity(intent);
            }
        });

        config = findViewById(R.id.ib_config_menu);
        config.setOnClickListener(v -> {
            Intent intentRecarga = new Intent(getApplicationContext(), Mudar_senha_app.class);
            finish();
            startActivity(intentRecarga);
        });
    }
}