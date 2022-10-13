package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Termos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView btnvoltar = findViewById(R.id.btnvoltar_termoseprivacidade);
        btnvoltar.setOnClickListener(view -> {

            Intent intentMainActivity = new Intent(getApplicationContext(), TermosEPrivacidade.class);
            finish();
            startActivity(intentMainActivity);

        });
    }
}