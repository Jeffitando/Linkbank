package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SegundaViaCartao extends AppCompatActivity {

    private ImageView img_car1, img_car2, img_car3, img_car4, img_car5;
    ImageButton ib_voltar_cartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_via_cartao);
        img_car1 = findViewById(R.id.img_car1);
        img_car2 = findViewById(R.id.img_car2);
        img_car3 = findViewById(R.id.img_car3);
        img_car4 = findViewById(R.id.img_car4);
        img_car5 = findViewById(R.id.img_car5);

        img_car1.setOnClickListener(v -> {
            Toast.makeText(this, "Cartão LinkBlue Selecionado com sucesso, em breve o banco entrará em contato.", Toast.LENGTH_LONG).show();
            Intent intentCard1 = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentCard1);
        });

        img_car2.setOnClickListener(v -> {
            Toast.makeText(this, "Cartão LinkBlack Selecionado com sucesso, em breve o banco entrará em contato.", Toast.LENGTH_LONG).show();
            Intent intentCard2 = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentCard2);
        });

        img_car3.setOnClickListener(v -> {
            Toast.makeText(this, "Cartão LinkOrange Selecionado com sucesso, em breve o banco entrará em contato.", Toast.LENGTH_LONG).show();
            Intent intentCard3 = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentCard3);
        });

        img_car4.setOnClickListener(v -> {
            Toast.makeText(this, "Cartão LinkPink Selecionado com sucesso, em breve o banco entrará em contato.", Toast.LENGTH_LONG).show();
            Intent intentCard4 = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentCard4);
        });

        img_car5.setOnClickListener(v -> {
            Toast.makeText(this, "Cartão LinkGreen Selecionado com sucesso, em breve o banco entrará em contato.", Toast.LENGTH_LONG).show();
            Intent intentCard5 = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentCard5);
        });

        ib_voltar_cartao = findViewById(R.id.ib_voltar_cartao);
        ib_voltar_cartao.setOnClickListener(view -> {
            Intent intentVoltarCartao = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intentVoltarCartao);

        });

    }
}