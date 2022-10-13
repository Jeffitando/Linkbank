package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Duvida_transferencia extends AppCompatActivity {

    private ImageButton ib_voltar_doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duvida_transferencia);



        ib_voltar_doc = findViewById(R.id.ib_voltar_doc);
        ib_voltar_doc.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),transferencia_menu.class);
            finish();
            startActivity(intent);
        });

    }
}