package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TelaDeCriacaoDeSenha extends AppCompatActivity {

    private Button btnEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_criacao_de_senha);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnEntrar = findViewById(R.id.entrarlogin);
        btnEntrar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PropostaEnviada.class);
            finish();
            startActivity(intent);
        });
    }
}