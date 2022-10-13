package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CartaoLimite extends AppCompatActivity {

    private TextView btnAjustar;
    private TextView tvLimiteAtual, tvLimite;
    private SeekBar seekBar;
    private ImageButton ib_voltar_limites;
    private Usuario usuario;
    private int limite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao_limite);

        tvLimite = findViewById(R.id.tvLimite);
        btnAjustar = findViewById(R.id.btnAjustar);
        seekBar = findViewById(R.id.seekBar);
        tvLimiteAtual = findViewById(R.id.tvLimiteAtual);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvLimiteAtual.setText("R$ " + "" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvLimiteAtual.setText("R$2000");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnAjustar = findViewById(R.id.btnAjustar);
        btnAjustar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDadosUsuario();
                Toast.makeText(CartaoLimite.this, "Limite Ajustado " + "" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }
        });

        ib_voltar_limites = findViewById(R.id.ib_voltar_limites);
        ib_voltar_limites.setOnClickListener(v -> {
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
            @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void configDados() {
        tvLimite.setText("Limite Máximo : R$ " + usuario.getRenda());
        int limiteInt = Integer.parseInt(usuario.getRenda());
        limite = Integer.parseInt(usuario.getLimite());
        seekBar.setMax(limiteInt);
        seekBar.setMin(limiteInt/4);
        seekBar.setProgress(limite);
        tvLimiteAtual.setText("R$ " + "" + limite);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperaDados();
    }
    private void salvarDadosUsuario(){

        usuario.setLimite(String.valueOf(seekBar.getProgress()));

        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(usuario.getId());
        usuarioRef.setValue(usuario).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(this, "Informações salvas com sucesso.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Não foi possível salvar as informações.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}