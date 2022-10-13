package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class duvida_pix extends AppCompatActivity {

    private ImageButton ib_voltar_pix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duvida_pix);


        ib_voltar_pix = findViewById(R.id.ib_voltar_pix);
        ib_voltar_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),pix_menu.class);
                finish();
                startActivity(intent);
            }
        });

    }
}