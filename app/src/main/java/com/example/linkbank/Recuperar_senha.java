package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.linkbank.adapter.helper.FirebaseHelper;

public class Recuperar_senha extends AppCompatActivity {

    ImageButton ib_voltar_login;
    TextView txt_recuperar_sms;
    ProgressBar progressBar3;
    EditText edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);


        edEmail = findViewById(R.id.edEmail);
        progressBar3 = findViewById(R.id.progressBar3);


        txt_recuperar_sms = findViewById(R.id.txt_recuperar_sms);
        txt_recuperar_sms.setOnClickListener(v -> {
            Intent intentSms = new Intent(getApplicationContext(), recuperar_senha_sms.class);
            finish();
            startActivity(intentSms);
        });


        ib_voltar_login = findViewById(R.id.ib_voltar_login);
        ib_voltar_login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaDeEntrada.class);
            finish();
            startActivity(intent);
        });
    }

    public void validaDados(View view) {

        String email = edEmail.getText().toString();

        if (!email.isEmpty()) {

            progressBar3.setVisibility(View.VISIBLE);


            recuperarConta(email);


        } else {
            edEmail.requestFocus();
            edEmail.setError("Informe seu e-mail");
        }


    }

    private void recuperarConta(String email) {



        FirebaseHelper.getAuth().sendPasswordResetEmail(
                email
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Acabamos de te enviar um link via e-mail.", Toast.LENGTH_SHORT).show();
            } else {
                progressBar3.setVisibility(View.GONE);
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }

        });


    }

}

