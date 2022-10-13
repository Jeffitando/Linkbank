package com.example.linkbank.transferencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.comprovante_ted;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.santalu.maskara.widget.MaskEditText;

public class ConfirmarTedActivity extends AppCompatActivity {

    private Button btnConfirmar;
    private TextView textData, textValor, textUsuario, textConta, textAgencia;
    private ImageButton ib_voltar_transferencia;

    private Usuario usuarioOrigem;
    private Transferencia transferencia;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_ted);

        textData = findViewById(R.id.textData);
        textValor = findViewById(R.id.textValor);
        textUsuario = findViewById(R.id.textUsuario);
        textConta = findViewById(R.id.textConta);
        textAgencia = findViewById(R.id.textAgencia);
        textUsuario = findViewById(R.id.textUsuario);
        String data = (String) getIntent().getSerializableExtra("data_transfeerncia");
        String valor = (String) getIntent().getSerializableExtra("valor_transfeerncia");
        String conta = (String) getIntent().getSerializableExtra("numero_conta");
        String agencia = (String) getIntent().getSerializableExtra("numero_agencia");
        String nome = (String) getIntent().getSerializableExtra("nome_usuario");
        textData.setText(data);
        textValor.setText(valor);
        textConta.setText(conta);
        textAgencia.setText(agencia);
        textUsuario.setText(nome);




        ib_voltar_transferencia = findViewById(R.id.ib_voltar_transferencia);
        ib_voltar_transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Transferir_ted.class);
                finish();
                startActivity(intentVoltar);

            }

        });

        findViewById(R.id.btnConfirmar).setOnClickListener(view -> {

            Intent intent = new Intent(this, comprovante_ted.class);

            intent.putExtra("data_transfeerncia", data);
            intent.putExtra("valor_transfeerncia", valor);
            intent.putExtra("numero_conta", conta);
            intent.putExtra("numero_agencia", agencia);
            intent.putExtra("nome_usuario", nome);
            startActivity(intent);


        });
    }




}