package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Sucesso_Emprestimo extends AppCompatActivity {

    private ImageView btn_emprestimo_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucesso_emprestimo);

        btn_emprestimo_home.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            finish();
            startActivity(intent);

        });
    }
}