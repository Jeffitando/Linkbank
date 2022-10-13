package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.consorcio.Consorcios;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Gera_menu2 extends AppCompatActivity {

    TextView txt_dados, txt_atendimento, txt_seguros, txt_emprestimos, txt_cartoes, txt_notificacao, txt_limites, txt_configurar, txt_configurar2, txt_consorcios;
    ImageButton ib_voltar_geralmenu;
    TextView nome;
    ImageView imgPerfil;
    private Usuario usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gera_menu2);


        txt_dados = findViewById(R.id.txt_meus_dados);
        txt_atendimento = findViewById(R.id.txt_atendimento);
        txt_seguros = findViewById(R.id.txt_seguranca);
        txt_emprestimos = findViewById(R.id.txt_emprestimos);
        txt_cartoes = findViewById(R.id.txt_cartaao);
        txt_notificacao = findViewById(R.id.txt_notificacao);
        txt_limites = findViewById(R.id.txt_meus_limites);
        txt_configurar = findViewById(R.id.txt_configurar);
        txt_configurar2 = findViewById(R.id.txt_configurar2);
        txt_consorcios = findViewById(R.id.txt_consorcio);
        ib_voltar_geralmenu = findViewById(R.id.ib_voltar_geralmenu);

        txt_dados.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Meus_dados.class);
            finish();
            startActivity(intent);
        });

        txt_atendimento.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Atendimento.class);
            finish();
            startActivity(intent);
        });

        txt_seguros = findViewById(R.id.txt_seguranca);
        txt_seguros.setOnClickListener(view -> {
            Intent intentSeguros = new Intent(getApplicationContext(), Seguros_menu.class);
            finish();
            startActivity(intentSeguros);
        });

        txt_emprestimos.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Emprestimo_valor.class);
            finish();
            startActivity(intent);
        });

        txt_cartoes.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Cartao.class);
            finish();
            startActivity(intent);
        });

        txt_notificacao.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NotificacaoMenu.class);
            intent.putExtra("chave_notificacao", "menu");
            finish();
            startActivity(intent);
        });

        txt_limites.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CartaoLimite.class);
            finish();
            startActivity(intent);
        });

        txt_configurar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Configurar_Pix.class);
            finish();
            startActivity(intent);
        });

        txt_consorcios.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Consorcios.class);
            finish();
            startActivity(intent);
        });

        txt_configurar2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TelaDeContas.class);
            finish();
            startActivity(intent);
        });

        ib_voltar_geralmenu.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            finish();
            startActivity(intent);

        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        recuperaDados();
    }

    private void recuperaDados(){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(FirebaseHelper.getIdFirebase());
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
                configDados();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configDados(){
        imgPerfil = findViewById(R.id.imgPerfil);
        nome = findViewById(R.id.tvNome);

        nome.setText(usuario.getNome());


        if (usuario.getUrlImagem().equals("")) {
            return;
        }else{
            Picasso.get().load(usuario.getUrlImagem())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.user)
                    .into(imgPerfil);
        }
    }
}
