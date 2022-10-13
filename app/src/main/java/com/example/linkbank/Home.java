package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.linkbank.adapter.AdapterExtrato;
import com.example.linkbank.adapter.ExtratoAdapter;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.cobrar.CobrarFormActivity;
import com.example.linkbank.extrato.ExtratoActivity;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.ItemExtrato;
import com.example.linkbank.model.Notificacao;
import com.example.linkbank.model.Usuario;
import com.example.linkbank.notificacoes.NotificacoesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Home extends AppCompatActivity {

    private final List<Notificacao> notificacaoList = new ArrayList<>();

    private ConstraintLayout btnPix, layoutRecarga, layoutDeposito;
    private ConstraintLayout btnCartao, layoutCobrar, layoutExtrato;
    private ConstraintLayout layoutPagamentos;
    private ConstraintLayout btnTransfer;
    private ImageView btnConfig, btnNotificacao, imgSair, imgPerfil;
    private Usuario usuario;
    private ProgressBar progressBar;
    private TextView textInfo, tvNome;
    private TextView textNotificacao;
    private Boolean hideSup = false;

    private final List<Extrato> extratoListTemp = new ArrayList<>();
    private final List<Extrato> extratoList = new ArrayList<>();
    private final List<Extrato> list3 = new ArrayList<>();
    private final List<Extrato> list2 = new ArrayList<>();

    private ImageView imgHide;//button
    private TextView tvSaldoConta;
    private LinearLayout layoutImgHide;

    private RecyclerView recyclerExtrato;
    private ExtratoAdapter extratoAdapter;
    private ExtratoAdapter extratoAdapterSaida;
    private ExtratoAdapter extratoAdapterEntrada;


    private TextView todasFiltro;
    private TextView entradaFiltro;
    private TextView saidaFiltro;
    boolean supportList2;
    boolean supportList3;
    private ImageView minhaConta;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    private TextView txt_saldo;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         getWindow().setStatusBarColor(ContextCompat.getColor(Home.this, R.color.barra));

        todasFiltro = findViewById(R.id.tvTodas);
        entradaFiltro = findViewById(R.id.tvEntrada);
        saidaFiltro = findViewById(R.id.tvSaida);
        textNotificacao = findViewById(R.id.textNotificacao);
        recyclerExtrato = findViewById(R.id.recyclerExtrato);

        configCliques();
        configRv();
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        todasFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extratoTodos();
            }
        });
        saidaFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extratoSaida();
            }
        });
        entradaFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extratoEntrada();
            }
        });

        findViewById(R.id.layoutExtrato).setOnClickListener(v -> {
            startActivity(new Intent(this, ExtratoActivity.class));
        });



        findViewById(R.id.layoutCobrar).setOnClickListener(v -> {
            startActivity(new Intent(this, CobrarFormActivity.class));
        });


        findViewById(R.id.minhaConta).setOnClickListener(v -> {
            startActivity(new Intent(this, Meus_dados.class));
        });


        imgSair = findViewById(R.id.imgSair);
        imgSair.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("chave_proposta", "true");
            editor.commit();




            finish();

        });



//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

                //Olho que esconde o valor da conta
                progressBar = findViewById(R.id.progressBar);
                imgHide = findViewById(R.id.imgHide);
                textInfo = findViewById(R.id.textInfo);
                tvSaldoConta = findViewById(R.id.tvSaldoConta);






//        //Localizando variaveis no código
//        txt_saldo = findViewById(R.id.tvSaldoConta);
//
//        //Definindo Saldo como o correspondente na classe Pessoa
//        txt_saldo.setText(String.valueOf(Pessoa.getSaldo()));


        layoutRecarga = findViewById(R.id.layoutRecarga);
        layoutRecarga.setOnClickListener(v -> {
            Intent intentRecarga = new Intent(getApplicationContext(), recarga_celular.class);

            startActivity(intentRecarga);
        });


        btnNotificacao = findViewById(R.id.imgNotifier);
        btnNotificacao.setOnClickListener(view -> {
            Intent intentNotifier = new Intent(getApplicationContext(), NotificacoesActivity.class);
            startActivity(intentNotifier);

        });

        btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(view -> {
            Intent intentConfig = new Intent(getApplicationContext(), Gera_menu2.class);
            startActivity(intentConfig);
        });

        layoutDeposito = findViewById(R.id.layoutDeposito);
        layoutDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDeposito = new Intent(getApplicationContext(), Deposito.class);

                startActivity(intentDeposito);
            }
        });

        layoutImgHide = findViewById(R.id.linearLayoutHide);
        layoutImgHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                olho();
            }
        });


        btnTransfer = findViewById(R.id.layoutTransfer);
        btnTransfer.setOnClickListener(view -> {
            Intent intentTransfer = new Intent(getApplicationContext(), transferencia_menu.class);
            startActivity(intentTransfer);
        });


        layoutPagamentos = findViewById(R.id.layoutPagamentos);
        layoutPagamentos.setOnClickListener(view -> {
            Intent intentPagar = new Intent(getApplicationContext(), Pagamentos.class);
            startActivity(intentPagar);
        });


        btnPix = findViewById(R.id.layoutPix);
        btnPix.setOnClickListener(view -> {
            Intent intentPix = new Intent(Home.this, pix_menu.class);
            startActivity(intentPix);

        });

        btnCartao = findViewById(R.id.layoutCartoes);
        btnCartao.setOnClickListener(view -> {
            Intent intentCartao = new Intent(Home.this, Cartao.class);
            startActivity(intentCartao);

        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        recuperaDados();
    }

    private void recuperaNotificacoes(){
        DatabaseReference notificacaoRef = FirebaseHelper.getDatabaseReference()
                .child("notificacoes")
                .child(FirebaseHelper.getIdFirebase());
        notificacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                notificacaoList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                        Notificacao notificacao = ds.getValue(Notificacao.class);
                        notificacaoList.add(notificacao);
                }

                if (notificacaoList.isEmpty()) {
                    textNotificacao.setText("0");
                    textNotificacao.setVisibility(View.GONE);
                } else {
                    textNotificacao.setText(String.valueOf(notificacaoList.size()));
                    textNotificacao.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configRv(){
        recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
        recyclerExtrato.setHasFixedSize(true);
        extratoAdapter = new ExtratoAdapter(extratoList, getBaseContext());
        recyclerExtrato.setAdapter(extratoAdapter);
    }

    private void recuperaExtrato() {
        DatabaseReference extratoRef = FirebaseHelper.getDatabaseReference()
                .child("extratos")
                .child(FirebaseHelper.getIdFirebase());
        extratoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    extratoList.clear();

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Extrato extrato = ds.getValue(Extrato.class);
                        extratoList.add(extrato);
                    }

                    textInfo.setText("");
                } else {
                    textInfo.setText("Nenhuma movimentação encontrada.");
                }

                Collections.reverse(extratoList);
                progressBar.setVisibility(View.GONE);
                extratoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



















    private void extratoSaida(){

        if (supportList3) {

            recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
            recyclerExtrato.setHasFixedSize(true);
            extratoAdapterSaida = new ExtratoAdapter(list3, getBaseContext());
            recyclerExtrato.setAdapter(extratoAdapterSaida);

        } else {

            for (int i = 0; i < extratoList.size(); i++) {
                list3.add(extratoList.get(i));
            }

            for (int i = 0; i < list3.size(); i++) {
                Extrato extrato = list3.get(i);
                String resposta = extrato.getTipo();
                if (!resposta.equals("SAIDA")) {
                    list3.remove(extrato);
                    i--;
                }
            }


            supportList3 = true;

            recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
            recyclerExtrato.setHasFixedSize(true);
            extratoAdapterSaida = new ExtratoAdapter(list3, getBaseContext());
            recyclerExtrato.setAdapter(extratoAdapterSaida);

        }
        entradaFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_btn_gradiente_azul));
        saidaFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.categoria_premium));
        todasFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_btn_gradiente_azul));
    }

    private void extratoEntrada(){

        if (supportList2) {

            recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
            recyclerExtrato.setHasFixedSize(true);
            extratoAdapterEntrada = new ExtratoAdapter(list2, getBaseContext());
            recyclerExtrato.setAdapter(extratoAdapterEntrada);

        } else {

            for (int i = 0; i < extratoList.size(); i++) {
                list2.add(extratoList.get(i));
            }

            for (int i = 0; i < list2.size(); i++) {
                Extrato extrato = list2.get(i);
                String resposta = extrato.getTipo();
                if (resposta.equals("SAIDA")) {
                    list2.remove(extrato);
                    i--;
                }
            }


            supportList2 = true;

            recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
            recyclerExtrato.setHasFixedSize(true);
            extratoAdapterEntrada = new ExtratoAdapter(list2, getBaseContext());
            recyclerExtrato.setAdapter(extratoAdapterEntrada);

        }
        entradaFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.categoria_premium));
        saidaFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_btn_gradiente_azul));
        todasFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_btn_gradiente_azul));
    }

    private void extratoTodos(){
        configRv();
        entradaFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_btn_gradiente_azul));
        saidaFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_btn_gradiente_azul));
        todasFiltro.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.categoria_premium));
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
                recuperaNotificacoes();
                recuperaExtrato();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    private void configCliques(){

        findViewById(R.id.btnConfig).setOnClickListener(v -> {
            if(usuario != null){
                Intent intent = new Intent(this, Gera_menu2.class);
                intent.putExtra("usuario", (Serializable) usuario);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Ainda estamos recuperando as informações.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void olho() {
        if (hideSup==false){

            tvSaldoConta.setText(getString(R.string.text_valor, GetMask.getValor(usuario.getSaldo())));
            imgHide.setImageDrawable(getResources().getDrawable(R.drawable.nohide));
            hideSup=true;
        }else{
            tvSaldoConta.setText("R$ ••••••");
            imgHide.setImageDrawable(getResources().getDrawable(R.drawable.hide));
            hideSup=false;
        }

}
}



