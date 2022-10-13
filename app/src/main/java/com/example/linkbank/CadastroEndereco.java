package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.linkbank.model.CEP;
import com.example.linkbank.model.CEPService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroEndereco extends AppCompatActivity {

    private Button btnEndereco;
    private Retrofit retrofit;
    private EditText etCep;
    private EditText etRua;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etEstado;
    private ImageButton btnVoltar;
    private EditText etNumero;
    private EditText etComplemento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);


        String nome = getIntent().getStringExtra("fireNome");
        String cpf = getIntent().getStringExtra("fireCpf");
        String email = getIntent().getStringExtra("fireEmail");
        String phone = getIntent().getStringExtra("firePhone");
        String renda = getIntent().getStringExtra("fireRenda");
        String profissao = getIntent().getStringExtra("fireProfissao");
        String nascimento = getIntent().getStringExtra("fireNascimento");
        String rg = getIntent().getStringExtra("fireRg");


        btnVoltar = findViewById(R.id.btnVoltar_Entrada);
        btnEndereco = findViewById(R.id.btnEndereco);
        etCep = findViewById(R.id.etCep);
        etRua = findViewById(R.id.etRua);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etEstado = findViewById(R.id.etEstado);
        etNumero = findViewById(R.id.etNumero);
        etComplemento = findViewById(R.id.etComplemento);

        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CadastroDadosPessoais.class);
            finish();
            startActivity(intent);
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        etCep.setOnFocusChangeListener((view, b) -> {
            if (!b) {
                String cep = etCep.getText().toString();
                recuperarCEPRetrofit(cep);


            } else {

            }
        });


        btnEndereco.setOnClickListener(view -> {





            boolean sup = true;
            String cep = etCep.getText().toString();
            String rua = etRua.getText().toString();
            String bairro = etBairro.getText().toString();
            String estado = etEstado.getText().toString();
            String numero = etNumero.getText().toString();
            String complemento = etComplemento.getText().toString();

            if(cep.isEmpty()){
                sup = false;
            }
            if(rua.isEmpty()){
                sup = false;
            }
            if(bairro.isEmpty()){
                sup = false;
            }
            if(estado.isEmpty()){
                sup = false;
            }
            if(numero.isEmpty()){
                sup = false;
            }
            if(complemento.isEmpty()){
                sup = false;
            }

            if(sup == false){
                Toast.makeText(this, "Preencha todos campos!", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(getApplicationContext(), EscolhaCartaoCadastro.class);
                intent.putExtra("fireNome", nome);
                intent.putExtra("fireCpf", cpf);
                intent.putExtra("fireEmail", email);
                intent.putExtra("firePhone", phone);
                intent.putExtra("fireRenda", renda);
                intent.putExtra("fireCep", etCep.getText().toString());
                intent.putExtra("fireRua", etRua.getText().toString());
                intent.putExtra("fireBairro", etBairro.getText().toString());
                intent.putExtra("fireEstado", etEstado.getText().toString());
                intent.putExtra("fireCidade", etCidade.getText().toString());
                intent.putExtra("fireNumero", etNumero.getText().toString());
                intent.putExtra("fireComplemento", etComplemento.getText().toString());
                intent.putExtra("fireNascimento", nascimento);
                intent.putExtra("fireProfissao", profissao);
                intent.putExtra("fireRg", rg);
                finish();
                startActivity(intent);
            }
        });
    }

    private void recuperarCEPRetrofit(String cep) {
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP(cep);

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()) {
                    CEP cep = response.body();
                    etCep.setText(cep.getCep());
                    etRua.setText(cep.getLogradouro());
                    etBairro.setText(cep.getBairro());
                    etCidade.setText(cep.getLocalidade());
                    etEstado.setText(cep.getUf());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}