package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TiposContasCards extends AppCompatActivity {

    ImageButton ib_voltar_tipo_contas, ib_config_menu;
    ConstraintLayout layout_cartao_super, layout_cartao_premium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_contas_cards);

        ib_voltar_tipo_contas = findViewById(R.id.ib_voltar_seguros_menu);
        ib_voltar_tipo_contas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cartao.class);
                finish();
                startActivity(intent);
            }
        });

        ib_config_menu = findViewById(R.id.ib_config_menu);
        ib_config_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Atendimento.class);
                finish();
                startActivity(intent);
            }
        });

        layout_cartao_super = findViewById(R.id.layout_cartao_super);
        layout_cartao_super.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Descricao_conta_super.class);
                finish();
                startActivity(intent);
            }
        });

        layout_cartao_premium = findViewById(R.id.layout_cartao_premium);
        layout_cartao_premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Descricao_conta_premium.class);
                finish();
                startActivity(intent);
            }
        });
    }
}