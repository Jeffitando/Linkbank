package com.example.linkbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Cartao extends AppCompatActivity {

    private ImageButton ib_voltar_cartao;
    private ImageView bgCartao;
    private TextView textView114;
    private Button btn_pagamento, btn_segunda_via, btn_seguro_cartao, btn_tiposContas;
    private Button btn_sacar_caixa, btn_bloquear_cartao, btn_trocar_senha_cartao;
    private Usuario usuario;


    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        recuperaDados();

        textView114 = findViewById(R.id.textView114);


        findViewById(R.id.btn_tiposContas).setOnClickListener(v -> {
//           // Intent intentTipos = new Intent(getApplicationContext(), TiposContasCards.class);
//            finish();
//            startActivity(intentTipos);
        });

        btn_seguro_cartao = findViewById(R.id.btn_seguro_cartao);
        btn_seguro_cartao.setOnClickListener(v -> {
            Intent intentSeguroCartao = new Intent(getApplicationContext(), SeguroDoCartao.class);
            finish();
            startActivity(intentSeguroCartao);
        });

        btn_segunda_via = findViewById(R.id.btn_segunda_via);
        btn_segunda_via.setOnClickListener(v -> {
            Intent intentSegundaVia = new Intent(getApplicationContext(), SegundaViaCartao.class);
            finish();
            startActivity(intentSegundaVia);
        });

        bgCartao = findViewById(R.id.imgCartao);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        String bgcartao = preferences.getString("chave_cartao", "blue");
        bgCartao.setBackground(ContextCompat.getDrawable(this, R.drawable.cardmodel1));
        if (bgcartao.equals("black")) {
            bgCartao.setBackground(ContextCompat.getDrawable(this, R.drawable.cardmodel2));
        } else if (bgcartao.equals("laranja")) {
            bgCartao.setBackground(ContextCompat.getDrawable(this, R.drawable.cardmodel3));
        } else if (bgcartao.equals("rosa")) {
            bgCartao.setBackground(ContextCompat.getDrawable(this, R.drawable.cardmodel4));
        } else if (bgcartao.equals("verde")) {
            bgCartao.setBackground(ContextCompat.getDrawable(this, R.drawable.cardmodel5));
        }


        btn_trocar_senha_cartao = findViewById(R.id.btn_trocar_senha_cartao);
        btn_trocar_senha_cartao.setOnClickListener(v -> {
            Intent intentTrocarsenhaCartao = new Intent(getApplicationContext(), trocar_senha_cartao.class);
            finish();
            startActivity(intentTrocarsenhaCartao);
        });

        btn_tiposContas = findViewById(R.id.btn_tiposContas);
        btn_tiposContas.setOnClickListener(view -> {
                    Intent intent = new Intent(getApplicationContext(), TiposContasCards.class);
                    finish();
                    startActivity(intent);

                });


        ib_voltar_cartao = findViewById(R.id.ib_voltar_cartao);
        ib_voltar_cartao.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            finish();
            startActivity(intent);

        });

        btn_pagamento = findViewById(R.id.btn_pagamento);
        btn_pagamento.setOnClickListener(v -> showWarningAlertDialog());

    }

    private void showWarningAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Cartao.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Cartao.this).inflate(
                R.layout.layout_warning_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Alerta");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Você deseja habilitar/desabilitar pagamento online?");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(v -> {
            // alertDialog.dismiss();
            Toast.makeText(Cartao.this, "Cartão habilitado para pagamentos online", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(v -> {
            // alertDialog.dismiss();
            Toast.makeText(Cartao.this, "Cartão desabiltado para pagamentos online", Toast.LENGTH_SHORT).show();
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        btn_sacar_caixa = findViewById(R.id.btn_sacar_caixa);
        btn_sacar_caixa.setOnClickListener(v -> showSacarAlertDialog());

    }

    private void showSacarAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Cartao.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Cartao.this).inflate(
                R.layout.layout_warning_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Alerta");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Você deseja habilitar/desabilitar saques em caixas eletrônicos?");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(v -> {
            alertDialog.dismiss();
            Toast.makeText(Cartao.this, "Saques em caixas eletrônicos habilitado", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(v -> {
            alertDialog.dismiss();
            Toast.makeText(Cartao.this, "Saques em caixas eletrônicos desabilitado", Toast.LENGTH_SHORT).show();
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


        btn_bloquear_cartao = findViewById(R.id.btn_bloquear_cartao);
        btn_bloquear_cartao.setOnClickListener(v -> showBloquearAlertDialog());


    }

    private void showBloquearAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Cartao.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Cartao.this).inflate(
                R.layout.layout_warning_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Alerta");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Você deseja realmente bloquear seu cartão?");
        ((Button) view.findViewById(R.id.buttonYes)).setText("Desbloquear");
        ((Button) view.findViewById(R.id.buttonNo)).setText("Bloquear");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(v -> {
            alertDialog.dismiss();
            Toast.makeText(Cartao.this, "Seu cartão foi desbloqueado com sucesso!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonNo).setOnClickListener(v -> {
            alertDialog.dismiss();
            Toast.makeText(Cartao.this, "Seu cartão foi bloqueado com sucesso!", Toast.LENGTH_SHORT).show();
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


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

    private void configDados() {

        textView114.setText(usuario.getNome());


}}

