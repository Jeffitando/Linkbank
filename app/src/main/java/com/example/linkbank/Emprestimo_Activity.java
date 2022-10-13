package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Emprestimo_Activity extends AppCompatActivity {

    private Button btnProx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimo);

        btnProx = findViewById(R.id.btn_verificar_emprestimo);
        btnProx.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Emprestimo_valor.class);
            startActivity(intent);
        });
    }
}