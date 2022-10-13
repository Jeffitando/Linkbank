package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class TermosEPrivacidade extends AppCompatActivity {

    Button termosBtn, privacidadeBtn;
    Button btnentrar;
    ImageButton btnVoltar;

    boolean termos, priv = false;
    Switch switchTermos, switchPriv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temos_eprivacidade);

        switchTermos = findViewById(R.id.switchTermos);
        switchPriv = findViewById(R.id.switchPrivacidade);

        switchTermos.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                termos = true;
            }else {
                termos = false;
            }
        });

        switchPriv.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                priv = true;
            }else {
                priv = false;
            }
        });





        btnentrar = findViewById(R.id.btnentrar);

        btnentrar.setOnClickListener(view -> {

            if (termos && priv){
            Intent intent = new Intent(getApplicationContext(), CadastroDadosPessoais.class);
            finish();
            startActivity(intent);}
            else{
                Toast.makeText(getApplicationContext(), "Concorde com os termos", Toast.LENGTH_SHORT).show();
            }
        });



        termosBtn = findViewById(R.id.termosBtn);
        privacidadeBtn = findViewById(R.id.privacidadeBtn);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        termosBtn.setOnClickListener(view -> {

            termosBtn.setBackgroundColor(Color.parseColor("#FC5F17"));
            privacidadeBtn.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

            Intent intent = new Intent(getApplicationContext(), Termos.class);
            finish();
            startActivity(intent);
        });

        privacidadeBtn.setOnClickListener(view -> {

            termosBtn.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            privacidadeBtn.setBackgroundColor(Color.parseColor("#FC5F17"));

            Intent intent = new Intent(getApplicationContext(), Privacidade.class);
            finish();
            startActivity(intent);

        });


        btnVoltar = findViewById(R.id.btnVoltar_Entrada);

        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TelaDeEntrada.class);
            finish();
            startActivity(intent);
        });


    }
}