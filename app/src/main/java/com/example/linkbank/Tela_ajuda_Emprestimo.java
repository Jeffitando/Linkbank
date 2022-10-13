package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_ajuda_Emprestimo extends AppCompatActivity {

    private ImageButton ib_voltar_emprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ajuda_emprestimo);

        ib_voltar_emprestimos = findViewById(R.id.ib_voltar_emprestimos);

        ib_voltar_emprestimos.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Emprestimo_valor.class);
            finish();
            startActivity(intent);

        });
    }
}