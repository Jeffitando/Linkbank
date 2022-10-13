package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class CrieSuaSenhaDeLogin extends AppCompatActivity {


    private FirebaseAuth mAuth;


    Button btn_nova_senha;
    EditText senha1, senha2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crie_sua_senha_de_login);

        senha1 = findViewById(R.id.novaSenha);
        senha2 = findViewById(R.id.novaSenha_confirmar);

        mAuth = FirebaseAuth.getInstance();

        String nome = getIntent().getStringExtra("fireNome");
        String cpf = getIntent().getStringExtra("fireCpf");
        String email = getIntent().getStringExtra("fireEmail");
        String phone = getIntent().getStringExtra("firePhone");
        String renda = getIntent().getStringExtra("fireRenda");
        String cep = getIntent().getStringExtra("fireCep");
        String nascimento = getIntent().getStringExtra("fireNascimento");
        String rg = getIntent().getStringExtra("fireRg");
        String profissao = getIntent().getStringExtra("fireProfissao");
        String numero = getIntent().getStringExtra("fireNumero");
        String rua = getIntent().getStringExtra("fireRua");
        String cidade = getIntent().getStringExtra("fireCidade");
        String bairro = getIntent().getStringExtra("fireBairro");
        String estado = getIntent().getStringExtra("fireEstado");
        String complemento = getIntent().getStringExtra("fireComplemento");




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_nova_senha = findViewById(R.id.btn_nova_senha);
        btn_nova_senha.setOnClickListener(v -> {



            String senhaConfirmar1 = senha1.getText().toString();
            String senhaConfirmar2 = senha1.getText().toString();


            if (senhaConfirmar1.equals(senhaConfirmar2)){

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setRg(rg);
                usuario.setComplemento(complemento);
                usuario.setNumero(numero);
                usuario.setRua(rua);
                usuario.setBairro(bairro);
                usuario.setEstado(estado);
                usuario.setCidade(cidade);
                usuario.setCpf(cpf);
                usuario.setProfissao(profissao);
                usuario.setRenda(renda);
                usuario.setSenha(senhaConfirmar1);
                usuario.setAniversario(nascimento);
                usuario.setTelefone(phone);
                usuario.setCep(cep);
              usuario.setUrlImagem("");
              usuario.setPixTelefone("");
              usuario.setPixEmail("");
              usuario.setPixCpf("");

                Integer valorLimite = Integer.parseInt(renda);
                Integer valorLimiteFinal = valorLimite/100*80;
                String limiteString = String.valueOf(valorLimiteFinal);

                usuario.setLimite(limiteString);


                cadastrarUsuario(usuario);


                Toast.makeText(this, "Senha aceita", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),PropostaEnviada.class);

            finish();
            startActivity(intent);
        }else if(senhaConfirmar1.length() < 6){
                Toast.makeText(this, "Mínimo de 6 digitos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Senhas diferentes", Toast.LENGTH_SHORT).show();
            }
        
        });





    }

    private void cadastrarUsuario(Usuario usuario) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                String id = task.getResult().getUser().getUid();
                usuario.setId(id);

                salvarDadosUsuario(usuario);



            } else {

                Toast.makeText(this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();


            }


        });
    }

    private void  salvarDadosUsuario(Usuario usuario)  {

        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                        .child("usuario")
                        .child(usuario.getId());
        usuarioRef.setValue(usuario).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                startActivity(new Intent(this, TelaDeEntrada.class));
            } else {

                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


            }
        });





    }



}