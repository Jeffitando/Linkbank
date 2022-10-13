package com.example.linkbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.cobrar.ExtratoAdapter;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.ItemExtrato;
import com.example.linkbank.transferencia.Transferir_ted;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class transferencia_menu extends AppCompatActivity {



    private ConstraintLayout  Transferir_ted_menu;
    private ConstraintLayout  layoutTransferir_doc;
    private ConstraintLayout  layoutDuvidas;

    private ImageButton ib_voltar_transferencia;

    private final List<Extrato> extratoList = new ArrayList<>();
    private final List<Extrato> extratoListTemp = new ArrayList<>();

    private RecyclerView recyclerExtrato;

    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapter;
    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapterTemp;

    private ProgressBar progressBar;
    private TextView textInfo, btn;

    boolean supportList3;
    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapterSaida;
    private final List<Extrato> list3 = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia_menu);

        iniciaComponentes();
        recuperaExtrato();
        configRv();


        Transferir_ted_menu = findViewById(R.id.Transferir_ted_menu);
        Transferir_ted_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Transferir_ted.class);
                intent.putExtra("tipo", "ted");
                finish();
                startActivity(intent);
            }
        });


        ib_voltar_transferencia = findViewById(R.id.ib_voltar_transferencia);
        ib_voltar_transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(),Home.class);

                finish();
                startActivity(intentVoltar);
            }
        });


        layoutTransferir_doc = findViewById(R.id.layoutTransferir_doc);
        layoutTransferir_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDoc = new Intent(getApplicationContext(), Transferir_ted.class);
                intentDoc.putExtra("tipo", "doc");
                finish();
                startActivity(intentDoc);
            }
        });

        layoutDuvidas = findViewById(R.id.layoutDuvidas);
        layoutDuvidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDuvidas = new Intent(getApplicationContext(),Duvida_transferencia.class);
                finish();
                startActivity(intentDuvidas);
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
                        if (!extrato.getOperacao().equals("TRANSFERENCIA")){
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
        recyclerExtrato = findViewById(R.id.recyclerTransferencia);
        progressBar = findViewById(R.id.progressBar);
        textInfo = findViewById(R.id.textInfo);
    }

}