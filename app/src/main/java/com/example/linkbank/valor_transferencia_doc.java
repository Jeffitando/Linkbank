package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class valor_transferencia_doc extends AppCompatActivity {


    private Button valorDoc;
    private ImageButton ib_voltar_doc;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_transferencia_doc);


        ib_voltar_doc = findViewById(R.id.ib_voltar_doc);
        ib_voltar_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), transferencia_menu.class);
                finish();
                startActivity(intentVoltar);
            }
        });

        valorDoc = findViewById(R.id.valorDoc);
        valorDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentValorDoc = new Intent(getApplicationContext(), comprovante_doc.class);
                finish();
                startActivity(intentValorDoc);
            }
        });






    }
}