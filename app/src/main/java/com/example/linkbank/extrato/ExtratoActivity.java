package com.example.linkbank.extrato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.Home;
import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.cobrar.ExtratoAdapter;
import com.example.linkbank.model.Extrato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtratoActivity extends AppCompatActivity {

    private final List<Extrato> extratoList = new ArrayList<>();
    private final List<Extrato> extratoListTemp = new ArrayList<>();
    private final List<Extrato> list3 = new ArrayList<>();
    private final List<Extrato> list2 = new ArrayList<>();

    private RecyclerView recyclerExtrato;

    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapter;
    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapterSaida;
    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapterEntrada;

    boolean supportList2;
    boolean supportList3;

    private ProgressBar progressBar;
    private TextView textInfo;
    private TextView todasFiltro;
    private TextView entradaFiltro;
    private TextView saidaFiltro;
    private ImageButton btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        iniciaComponentes();

        configRv();

        recuperaExtrato();

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
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intent);
            }
        });

    }

    private void configRv(){
        recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
        recyclerExtrato.setHasFixedSize(true);
        extratoAdapter = new com.example.linkbank.adapter.ExtratoAdapter(extratoList, getBaseContext());
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



    private void iniciaComponentes() {
        btnVoltar = findViewById(R.id.ib_voltar_extrato);
        progressBar = findViewById(R.id.progressBar);
        textInfo = findViewById(R.id.textInfo);
        recyclerExtrato = findViewById(R.id.rvExtrato);
        todasFiltro = findViewById(R.id.tvTodas);
        entradaFiltro = findViewById(R.id.tvEntrada);
        saidaFiltro = findViewById(R.id.tvSaida);
    }


    private void extratoSaida(){

        if (supportList3) {

            recyclerExtrato.setLayoutManager(new LinearLayoutManager(this));
            recyclerExtrato.setHasFixedSize(true);
            extratoAdapterSaida = new com.example.linkbank.adapter.ExtratoAdapter(list3, getBaseContext());
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
            extratoAdapterSaida = new com.example.linkbank.adapter.ExtratoAdapter(list3, getBaseContext());
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
            extratoAdapterEntrada = new com.example.linkbank.adapter.ExtratoAdapter(list2, getBaseContext());
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
            extratoAdapterEntrada = new com.example.linkbank.adapter.ExtratoAdapter(list2, getBaseContext());
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


}