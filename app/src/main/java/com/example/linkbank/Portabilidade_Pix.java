package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Portabilidade_Pix extends AppCompatActivity {


    private ImageButton ib_voltar_pix_chaves;
    private Switch switch6, switch5, switch4;
    private EditText etCpf, etTel, etEmail;
    private ImageView editNum, editEmail;
    private boolean cpfSup, emailSup, telefoneSup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portabilidade_pix);


        switch6 = findViewById(R.id.switch6);
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Chave PIX com CPF ativa", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Chave PIX com CPF desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });


        switch5 = findViewById(R.id.switch5);
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Chave PIX com Telefone ativa", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Chave PIX com Telefone desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });



        switch4 = findViewById(R.id.switch4);
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Chave PIX com E-mail ativa", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Chave PIX com E-mail desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });






        ib_voltar_pix_chaves = findViewById(R.id.ib_voltar_pix_chaves);
        ib_voltar_pix_chaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Configurar_Pix.class);
                finish();
                startActivity(intent);
            }
        });

        etCpf = findViewById(R.id.etCpf);
        etEmail = findViewById(R.id.etEmail);
        etTel = findViewById(R.id.etTel);

        editNum = findViewById(R.id.imgEditNum);
        editEmail = findViewById(R.id.imgEditEmail);


        editNum.setOnClickListener(view -> {
            if (cpfSup) {
                etCpf.setEnabled(false);
                etCpf.clearFocus();
                cpfSup = false;

                etEmail.setEnabled(false);
                etEmail.clearFocus();
                emailSup = false;
            } else {
                etCpf.setEnabled(true);
                etCpf.requestFocus();
                cpfSup = true;

                etEmail.setEnabled(false);
                etEmail.clearFocus();
                emailSup = false;
            }
        });

        editEmail.setOnClickListener(view -> {
            if (emailSup) {
                etCpf.setEnabled(false);
                etCpf.clearFocus();
                cpfSup = false;

                etEmail.setEnabled(false);
                etEmail.clearFocus();
                emailSup = false;
            } else{
                etCpf.setEnabled(false);
                etCpf.requestFocus();
                cpfSup = false;

                etEmail.setEnabled(true);
                etEmail.clearFocus();
                emailSup = true;
            }
        });
    }
}