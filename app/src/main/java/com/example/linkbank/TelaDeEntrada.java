package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.adapter.helper.FirebaseHelper;

public class TelaDeEntrada extends AppCompatActivity {

    private TextView tv_recuperarconta, loginEmail, loginsenha;
    private ProgressBar progressBar;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    private String cadastro = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_entrada);



        loginEmail = findViewById(R.id.loginEmail);
        loginsenha = findViewById(R.id.loginsenha);
        progressBar = findViewById(R.id.progressBar);



        tv_recuperarconta = findViewById(R.id.tv_recuperarconta);
        tv_recuperarconta.setOnClickListener(view -> {
            Intent intentConfig = new Intent(getApplicationContext(), Recuperar_senha.class);
            startActivity(intentConfig);
        });

        Intent intent = new Intent(getApplicationContext(), TelaLogin.class);
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        String cadastro2 = preferences.getString("chave_cadastro", "false");
        String proposta = preferences.getString("chave_proposta", "false");

        if (cadastro2.equals("true")) {
            finish();
            startActivity(intent);

        } else {

            if (proposta.equals("true")) {
                Intent intent2 = new Intent(getApplicationContext(), PropostaAprovada.class);
            }

            //Força o app a ficar no modo Portrait
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);






            TextView tvegistrar = findViewById(R.id.tv_criarconta);
            tvegistrar.setOnClickListener(view -> {

                Intent intentMainAcitivity = new Intent(getApplicationContext(), TermosEPrivacidade.class);
                finish();
                startActivity(intentMainAcitivity);

            });


        }


    }

    public void validaDados(View view){
        String email = loginEmail.getText().toString();
        String senha = loginsenha.getText().toString();

        if (!email.isEmpty()){
            if (!senha.isEmpty()){

                progressBar.setVisibility(View.VISIBLE);

                logar(email, senha);


            } else  {
                loginsenha.requestFocus();
                loginsenha.setError("Informe sua senha");
            }

        } else  {
            loginEmail.requestFocus();
            loginEmail.setError("Informe seu e-mail");
        }

    }

    private void logar(String email, String senha) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                email, senha
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                finish();
                startActivity(new Intent(this, Home.class));
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Senha ou e-mail incorretos", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

