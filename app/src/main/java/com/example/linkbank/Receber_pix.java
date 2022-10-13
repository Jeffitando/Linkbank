package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Receber_pix extends AppCompatActivity {

    private ImageButton ib_voltar_receber_pix;
    private Button avancar_receber_pix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receber_pix);

        avancar_receber_pix = findViewById(R.id.avancar_receber_pix);
        avancar_receber_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Cobranca_pix.class);
                finish();
                startActivity(intent1);
            }
        });

        ib_voltar_receber_pix = findViewById(R.id.ib_voltar_receber_pix);
        ib_voltar_receber_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pix_menu.class);
                finish();
                startActivity(intent);
            }
        });


    }
}