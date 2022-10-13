package com.example.linkbank;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.ItemExtrato;
import com.example.linkbank.model.PagamentoExtratoExemplo;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pix_menu extends AppCompatActivity {

    private  ConstraintLayout Transferir_pix;
    private  ConstraintLayout layoutReceber_pix;
    private  ConstraintLayout chavesPix;
    private  ConstraintLayout layoutQrCode;
    private ImageButton duvidaPix;
    private ImageButton ib_voltarPix;

    private Usuario usuario;


    private final List<Extrato> extratoList = new ArrayList<>();

    private RecyclerView recyclerExtrato;

    private com.example.linkbank.adapter.ExtratoAdapter extratoAdapter;


    private ProgressBar progressBar;
    private TextView textInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_menu);

        recuperaUsuario();
        iniciaComponentes();
        recuperaExtrato();
        configRv();

        chavesPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chaveHome = "pixHome";
                Intent intentChaves = new Intent(getApplicationContext(), ChavesPixAtivas.class);
                intentChaves.putExtra("pixAct", chaveHome);
                finish();
                startActivity(intentChaves);
            }
        });



        ib_voltarPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarPix = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentVoltarPix);
            }
        });



        layoutReceber_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReceberPix = new Intent(getApplicationContext(), Receber_pix.class);
                finish();
                startActivity(intentReceberPix);
            }
        });


        layoutQrCode.setOnClickListener(v ->
                {
                    scanCode();
                }
        );

        Transferir_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentduvidaPix2 = new Intent(getApplicationContext(), Pix.class);
                startActivity(intentduvidaPix2);
            }
        });


        duvidaPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentduvidaPix = new Intent(getApplicationContext(), duvida_pix.class);
                startActivity(intentduvidaPix);
            }
        });
    }


    private void scanCode() {

        ScanOptions options = new ScanOptions();
        options.setPrompt("Pressione o botão de volume para ativar o flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result ->
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(pix_menu.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(pix_menu.this).inflate(
                R.layout.layout_qr_code,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Transferir com Pix");
        //((TextView) view.findViewById(R.id.textMessage)).setText("Você deseja habilitar/desabilitar pagamento online?");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_pix_24);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(pix_menu.this, "Pagamento cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                salvarExtrato();
                Toast.makeText(pix_menu.this, "Pagamento realizado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


    });





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
                        if (!extrato.getOperacao().equals("PIX")){
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
        recyclerExtrato = findViewById(R.id.recyclerPix);
        progressBar = findViewById(R.id.progressBar);
        textInfo = findViewById(R.id.textInfo);
        duvidaPix = findViewById(R.id.duvida_pix);
        Transferir_pix = findViewById(R.id.Transferir_pix);
        layoutQrCode = findViewById(R.id.layoutQrCode);
        layoutReceber_pix = findViewById(R.id.layoutReceber_pix);
        ib_voltarPix = findViewById(R.id.ib_voltarPix);
        chavesPix = findViewById(R.id.chavesPix);
    }

    private void salvarExtrato(){

        Extrato extrato = new Extrato();
        extrato.setOperacao("PIX");
        extrato.setValor(100);
        extrato.setTipo("SAIDA");

        DatabaseReference extratoRef = FirebaseHelper.getDatabaseReference()
                .child("extratos")
                .child(FirebaseHelper.getIdFirebase())
                .child(extrato.getId());
        extratoRef.setValue(extrato).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                DatabaseReference updateExtrato = extratoRef
                        .child("data");
                updateExtrato.setValue(ServerValue.TIMESTAMP);

                salvarPagamento(extrato);

            }
        });

    }

    private void salvarPagamento(Extrato extrato) {

        PagamentoExtratoExemplo pagamento = new PagamentoExtratoExemplo();
        pagamento.setId(extrato.getId());
        pagamento.setValor(extrato.getValor());

        DatabaseReference pagamentoRef = FirebaseHelper.getDatabaseReference()
                .child("pix")
                .child(pagamento.getId());

        pagamentoRef.setValue(pagamento).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                DatabaseReference updatePagamento = pagamentoRef
                        .child("data");
                updatePagamento.setValue(ServerValue.TIMESTAMP);
                usuario.setSaldo(usuario.getSaldo() - pagamento.getValor());
                usuario.atualizarSaldo();

                Intent intent = new Intent(this, pix_menu.class);
                startActivity(intent);
                finish();

            }
        });

    }
    private void recuperaUsuario(){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(FirebaseHelper.getIdFirebase());
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }







}