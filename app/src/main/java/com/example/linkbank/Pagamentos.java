package com.example.linkbank;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Extrato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pagamentos extends AppCompatActivity {


    private ImageButton ib_voltar_pagamentos;
    private ConstraintLayout duvidas_boletos;
    private ConstraintLayout digitarBoleto;
    private ConstraintLayout lerCodigo;

    private RecyclerView recyclerExtrato;
    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapter;
    private final List<Extrato> extratoList = new ArrayList<>();

    private ProgressBar progressBar;
    private TextView textInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos);

        iniciaComponentes();
        recuperaExtrato();
        configRv();





        duvidas_boletos = findViewById(R.id.duvidas_boletos);
        duvidas_boletos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intentDuvidasBoleto = new Intent(getApplicationContext(), Duvidas_boletos.class);
                finish();
                startActivity(intentDuvidasBoleto);
            }
        });

        digitarBoleto = findViewById(R.id.layout_digitarBoleto);
        digitarBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDigitarBoleto = new Intent(getApplicationContext(), Digitar_codigo_barras.class);
                finish();
                startActivity(intentDigitarBoleto);
            }
        });


        ib_voltar_pagamentos = findViewById(R.id.ib_voltar_pagamentos);
        ib_voltar_pagamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentVoltar);
            }
        });


        lerCodigo = findViewById(R.id.lerCodigo);


        lerCodigo.setOnClickListener(onClickListener);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.lerCodigo:
                    new IntentIntegrator(Pagamentos.this).initiateScan();
                    break;
            }


        }
    };

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
                        if (!extrato.getOperacao().equals("PAGAMENTO")){
                            extratoList.remove(extrato);
                        }
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
        recyclerExtrato = findViewById(R.id.recyclerPagamentos);
        progressBar = findViewById(R.id.progressBar);
        textInfo = findViewById(R.id.textInfo);
    }

}