package com.example.linkbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.example.linkbank.transferencia.ValorTedActivity;

public class Digitar_codigo_barras extends AppCompatActivity {

    private Button avancar_boletos;
    private ImageButton ib_voltar_boletos;
    private EditText etDat, etDat2, etDat3, etDat4, etDat5;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitar_codigo_barras);

        iniciaComponentes();


        avancar_boletos = findViewById(R.id.avancar_boletos);
        avancar_boletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaDados();
            }
        });


        ib_voltar_boletos = findViewById(R.id.ib_digitar_codigos);
        ib_voltar_boletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),Pagamentos.class);
                finish();
                startActivity(intent2);
            }
        });

    }

    private void validaDados(){
        String dat = etDat.getText().toString();
        String dat2 = etDat2.getText().toString();
        String dat3 = etDat3.getText().toString();
        String dat4 = etDat4.getText().toString();
        String dat5 = etDat5.getText().toString();

        if (!dat.isEmpty()){
            if (!dat2.isEmpty()){
                if (!dat3.isEmpty()){
                    if (!dat4.isEmpty()){
                        if (!dat5.isEmpty()){

                            Intent intent = new Intent(getApplicationContext(),Dados_boleto.class);
                            finish();
                            startActivity(intent);

                            }else{showDialog();}
                        }else{showDialog();}
                    }else{showDialog();}
                }else{showDialog();}
            }else{showDialog();}

        }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );



        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Atenção");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText("Preencha todos os campos");



        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }


    private void iniciaComponentes() {
        etDat = findViewById(R.id.etDat);
        etDat2 = findViewById(R.id.etDat2);
        etDat3 = findViewById(R.id.etDat3);
        etDat4 = findViewById(R.id.etDat4);
        etDat5 = findViewById(R.id.etDat5);

    }
}