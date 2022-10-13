package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkbank.adapter.ExtratoAdapter;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conta_poupanca extends AppCompatActivity {

    private ImageView btnConfig;
    TextView tvprogramar, tvResgatar, tvAplicar, tvExtrato;
    Usuario usuario;
    private TextView textInfo, tvNome;
    private ImageView minhaConta, imgPerfil;
    private ProgressBar progressBar;
    private ImageView imgHide;//button
    private TextView tvSaldoConta;
    private RecyclerView recyclerExtrato;
    private LinearLayout layoutImgHide;
    private final List<Extrato> extratoList = new ArrayList<>();
    private ExtratoAdapter extratoAdapter;
    private Boolean hideSup = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_poupanca);



        recyclerExtrato = findViewById(R.id.recyclerExtrato);

        progressBar = findViewById(R.id.progressBar);
        imgHide = findViewById(R.id.imgHide);
        textInfo = findViewById(R.id.textInfo);
        tvSaldoConta = findViewById(R.id.tvSaldoConta);


        tvExtrato = findViewById(R.id.tvExtrato);
        tvExtrato.setOnClickListener(v -> {
            Intent intentExtrato = new Intent(getApplicationContext(), PoupancaExtrato.class);
            finish();
            startActivity(intentExtrato);
        });

        layoutImgHide = findViewById(R.id.linearLayoutHide);
        layoutImgHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                olho();
            }
        });

        tvAplicar = findViewById(R.id.tvAplicar);
        tvAplicar.setOnClickListener(v -> {
            Intent intentAplicar = new Intent(getApplicationContext(), PoupancaAplicar.class);
            finish();
            startActivity(intentAplicar);
        });

        tvResgatar = findViewById(R.id.tvResgatar);
        tvResgatar.setOnClickListener(v -> {
            Intent intentResgatar = new Intent(getApplicationContext(), PoupancaResgatar.class);
            finish();
            startActivity(intentResgatar);
        });

        tvprogramar = findViewById(R.id.tvprogramar);
        tvprogramar.setOnClickListener(v -> {
            Intent intentProgramar = new Intent(getApplicationContext(), PoupancaProgramar.class);
            finish();
            startActivity(intentProgramar);
        });

        btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intent);
        });


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


    @Override
    protected void onStart() {
        super.onStart();

        recuperaDados();
    }


    public void olho() {
        if (hideSup==false){

            tvSaldoConta.setText(getString(R.string.text_valor, GetMask.getValor(6000   )));
            imgHide.setImageDrawable(getResources().getDrawable(R.drawable.nohide));
            hideSup=true;
        }else{
            tvSaldoConta.setText("R$ ••••••");
            imgHide.setImageDrawable(getResources().getDrawable(R.drawable.hide));
            hideSup=false;
        }

    }



    private void configDados(){

        tvSaldoConta.setText("R$ ••••••");
        imgPerfil = findViewById(R.id.minhaConta);
        tvNome = findViewById(R.id.tvUsername);

        tvNome.setText(usuario.getNome());


        if (usuario.getUrlImagem().equals("")) {
            return;
        }else{
            Picasso.get().load(usuario.getUrlImagem())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.user)
                    .into(imgPerfil);
        }
        textInfo.setText("");
        progressBar.setVisibility(View.GONE);
    }


}