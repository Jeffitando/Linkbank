package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class trocar_senha_cartao extends AppCompatActivity {

    private ImageButton ib_voltar_trocar_senha_cartao;
    private Button trocar_senha4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha_cartao);

        trocar_senha4 = findViewById(R.id.trocar_senha4);
        trocar_senha4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        ib_voltar_trocar_senha_cartao = findViewById(R.id.ib_voltar_trocar_senha_cartao);
        ib_voltar_trocar_senha_cartao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intent);
        });

    }


}