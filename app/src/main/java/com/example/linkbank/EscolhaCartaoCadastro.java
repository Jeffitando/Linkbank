package com.example.linkbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EscolhaCartaoCadastro extends AppCompatActivity {

    private ImageView imgCartao;
    private ImageView imgCartao2;
    private ImageView imgCartao3;
    private ImageView imgCartao4;
    private ImageView imgCartao5;
    private String nome, cpf, email, phone, renda, profissao, nascimento, rg, cep, rua, bairro, estado, cidade, numero, complemento;
    SharedPreferences preferences;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_cartao_cadastro);


        preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        nome = getIntent().getStringExtra("fireNome");
         cpf = getIntent().getStringExtra("fireCpf");
         email = getIntent().getStringExtra("fireEmail");
         phone = getIntent().getStringExtra("firePhone");
         renda = getIntent().getStringExtra("fireRenda");
         profissao = getIntent().getStringExtra("fireProfissao");
         nascimento = getIntent().getStringExtra("fireNascimento");
         rg = getIntent().getStringExtra("fireRg");
         cep = getIntent().getStringExtra("fireCep");
         rua = getIntent().getStringExtra("fireRua");
         bairro = getIntent().getStringExtra("fireBairro");
         estado = getIntent().getStringExtra("fireEstado");
         cidade = getIntent().getStringExtra("fireCidade");
         numero = getIntent().getStringExtra("fireNumero");
         complemento = getIntent().getStringExtra("fireComplemento");







        imgCartao = findViewById(R.id.imgCartao);
        imgCartao.setOnClickListener(view -> {
            escolher("blue");
        });

        imgCartao2 = findViewById(R.id.imgCartao2);
        imgCartao2.setOnClickListener(view -> {
            escolher("laranja");
        });

        imgCartao3 = findViewById(R.id.imgCartao3);
        imgCartao3.setOnClickListener(view -> {
            escolher("verde");
        });

        imgCartao4 = findViewById(R.id.imgCartao4);
        imgCartao4.setOnClickListener(view -> {
            escolher("black");
        });

        imgCartao5 = findViewById(R.id.imgCartao5);
        imgCartao5.setOnClickListener(view -> {
            escolher("rosa");
        });

       /* imgCartao2 = findViewById(R.id.imgCartao2);
        imgCartao2.setOnClickListener(view -> {
            Intent intentImgCartao2 = new Intent(getApplicationContext(), cartao_personalizado_laranja.class);
            finish();
            startActivity(intentImgCartao2);
        });*/

    }

    private void escolher(String cor){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("chave_cartao", cor);
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), CrieSuaSenhaDeLogin.class);
        intent.putExtra("fireNome", nome);
        intent.putExtra("fireCpf", cpf);
        intent.putExtra("fireEmail", email);
        intent.putExtra("firePhone", phone);
        intent.putExtra("fireRenda", renda);
        intent.putExtra("fireCep", cep);
        intent.putExtra("fireRua", rua);
        intent.putExtra("fireBairro", bairro);
        intent.putExtra("fireEstado", estado);
        intent.putExtra("fireCidade", cidade);
        intent.putExtra("fireNumero", numero);
        intent.putExtra("fireComplemento", complemento);
        intent.putExtra("fireNascimento", nascimento);
        intent.putExtra("fireProfissao", profissao);
        intent.putExtra("fireRg", rg);
        finish();
        startActivity(intent);


    }
}