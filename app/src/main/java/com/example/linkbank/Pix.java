package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Pix extends AppCompatActivity {

    private ImageButton ib_voltarPix;
    private ImageButton duvida_pix;
    private Button btnCpf;
    private Button btnEmail;
    private Button btnChaveAleatoria;
    private Button btnTelefone;
    private TextView tvSaldoConta;

    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix);

        tvSaldoConta = findViewById(R.id.tvSaldo);

        btnChaveAleatoria = findViewById(R.id.btnChaveAleatoria);
        btnChaveAleatoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChaves = new Intent(getApplicationContext(), Cpf_pix.class);
                finish();
                startActivity(intentChaves);
            }
        });



        btnTelefone = findViewById(R.id.btnTelefone);
        btnTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChaves = new Intent(getApplicationContext(), Telefone_pix.class);
                finish();
                startActivity(intentChaves);
            }
        });



        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoPix = "email";
                Intent intentChaves = new Intent(getApplicationContext(), Email_pix.class);
                intentChaves.putExtra("tipoPix", tipoPix);
                finish();
                startActivity(intentChaves);
            }
        });


        duvida_pix = findViewById(R.id.duvida_pix);
        duvida_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar  = new Intent(getApplicationContext(), com.example.linkbank.duvida_pix.class);
                finish();
                startActivity(intentVoltar);
            }
        });

        btnCpf = findViewById(R.id.btnCpf);
        btnCpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoPix = "cpf";
                Intent intentChaves = new Intent(getApplicationContext(), Cpf_pix.class);
                intentChaves.putExtra("tipoPix", tipoPix);
                finish();
                startActivity(intentChaves);
            }
        });

        ib_voltarPix = findViewById(R.id.ib_voltarPix);

        ib_voltarPix.setOnClickListener(view -> {
            Intent intentPix = new Intent(getApplicationContext(), Home.class);
            startActivity(intentPix);
            finish();
        });
    }

    private void recuperaDados(){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(FirebaseHelper.getIdFirebase());
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(Usuario.class);
                configDados();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configDados(){

        tvSaldoConta.setText(getString(R.string.text_valor, GetMask.getValor(user.getSaldo())));

    }
    @Override
    protected void onStart() {
        super.onStart();

        recuperaDados();
    }
}