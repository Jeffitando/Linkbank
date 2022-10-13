package com.example.linkbank.consorcio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.linkbank.Home;
import com.example.linkbank.R;

public class ConsorcioAnalise extends AppCompatActivity {

    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consorcio_analise);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(getApplicationContext(), Home.class);
            startActivity(intentHome);
        });

    }
}