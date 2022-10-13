package com.example.linkbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Atendimento extends AppCompatActivity {

    ImageButton ib_voltar_atendimento;
    TextView btnLigar1, btnLigar2, btnLigar3, btnLigar4, btnLigar5, btnLigar6;
    ImageView btnWhatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento);

        btnLigar1 = findViewById(R.id.tvLigacao1);
        btnLigar2 = findViewById(R.id.tvLigacao2);
        btnLigar3 = findViewById(R.id.tvLigacao3);
        btnLigar4 = findViewById(R.id.tvLigacao4);
        btnLigar5 = findViewById(R.id.tvLigacao5);
        btnLigar6 = findViewById(R.id.tvLigacao6);
        btnWhatsapp = findViewById(R.id.imgWhatsapp);

        btnLigar1.setOnClickListener(view -> dialogContato(btnLigar1.getText().toString()));

        btnLigar2.setOnClickListener(view -> dialogContato(btnLigar2.getText().toString()));

        btnLigar3.setOnClickListener(view -> dialogContato(btnLigar3.getText().toString()));

        btnLigar4.setOnClickListener(view -> dialogContato(btnLigar4.getText().toString()));

        btnLigar5.setOnClickListener(view -> dialogContato(btnLigar5.getText().toString()));

        btnLigar6.setOnClickListener(view -> dialogContato(btnLigar6.getText().toString()));

        btnWhatsapp.setOnClickListener(view -> whatsapp());


        ib_voltar_atendimento = findViewById(R.id.ib_voltar_atendimento);

        ib_voltar_atendimento.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intent);

        });


    }

    private void dialogContato(String numero) {

        String contato = numero.replace(" ", "").replace("-", "");
        StringBuffer numeroContato = new StringBuffer(contato);

        ligar(numeroContato);

    }

    private void ligar(StringBuffer numeroContato) {
        Uri uri = Uri.parse("tel:" + numeroContato);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    private void whatsapp() {
        String url = "https://api.whatsapp.com/send?phone=992029139";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


}