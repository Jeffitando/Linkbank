package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.linkbank.transferencia.ValorTedActivity;
import com.example.linkbank.transferencia.Valor_transferencia;
import com.google.android.material.textfield.TextInputEditText;
import com.santalu.maskara.widget.MaskEditText;

public class transferir_doc extends AppCompatActivity {


    private ImageButton ib_voltar_transferencia;
    private ImageView iv_favoritos;
    private MaskEditText NumeroConta, numeroAgencia;
    private TextInputEditText Nome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_doc);


        NumeroConta = findViewById(R.id.NumeroConta);
        numeroAgencia = findViewById(R.id.numeroAgencia);
        Nome = findViewById(R.id.Nome);






        iv_favoritos = findViewById(R.id.iv_favoritos);
        iv_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFavoritos = new Intent(getApplicationContext(), Valor_transferencia.class);
                finish();
                startActivity(intentFavoritos);
            }
        });

        ib_voltar_transferencia = findViewById(R.id.ib_voltar_transferencia);
        ib_voltar_transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarHomeTed = new Intent(getApplicationContext(), transferencia_menu.class);
                finish();
                startActivity(intentVoltarHomeTed);
            }
        });

        findViewById(R.id.avancar_ted).setOnClickListener(view -> {
            String conta = NumeroConta.getText().toString();
            String agencia = numeroAgencia.getText().toString();
            String nome = Nome.getText().toString();








            Intent intent = new Intent(this, ValorTedActivity.class);
            intent.putExtra("numero_conta", conta);
            intent.putExtra("numero_agencia", agencia);
            intent.putExtra("nome_usuario", nome);


            startActivity(intent);

        });

    }
}