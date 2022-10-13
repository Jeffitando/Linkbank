package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tela_Caso_ja_tenha_uma_conta extends AppCompatActivity {

    TextView tvEsqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_caso_ja_tenha_uma_conta);

        //Força o app a ficar no modo Portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        Button entrarlogin3 = findViewById(R.id.entrarlogin3);
        entrarlogin3.setOnClickListener(view -> {

            Intent intentMainAcitivity = new Intent(getApplicationContext(), Home.class);

            startActivity(intentMainAcitivity);


        });


        tvEsqueciSenha = findViewById(R.id.tvEsqueciSenha);
        tvEsqueciSenha.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),Recuperar_senha.class);
            finish();
            startActivity(intent);
        });
    }
}