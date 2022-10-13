package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Mudar_senha_app extends AppCompatActivity {

    private Button trocar_senha5;
    private ImageButton ib_voltar_seguros_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_senha_app);


        findViewById(R.id.ib_voltar_seguros_menu).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaDeContas.class);
            finish();
            startActivity(intent);
        });


        trocar_senha5 = findViewById(R.id.trocar_senha5);
        trocar_senha5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Mudar_senha_app.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

    }
}