package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TelaLogin extends AppCompatActivity {

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    private String cadastro = "false";
    private TextView btnSemconta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);


        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        btnSemconta = findViewById(R.id.tvnaotemconta);
        btnSemconta.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("chave_cadastro", "false");
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), TelaDeEntrada.class);

            startActivity(intent);
        });

        //Força o app a ficar no modo Portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button entrarlogin2 = findViewById(R.id.entrarlogin2);
        entrarlogin2.setOnClickListener(view -> {

            Intent intentMainAcitivity = new Intent(getApplicationContext(), Tela_Caso_ja_tenha_uma_conta.class);

            startActivity(intentMainAcitivity);

        });

        //update

    }
}