package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ChavesPixAtivas extends AppCompatActivity {

    private Button btn_salvar_portabilidade_pix;
    private ImageButton ib_voltar_pix_chaves;
    private Switch switch6, switch5, switch4;

    private EditText etTel;
    private EditText etCpf;
    private EditText etEmail;

    private String pixActivity;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaves_pix_ativas);

        pixActivity = getIntent().getStringExtra("pixAct");


        switch6 = findViewById(R.id.switch6);
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Chave PIX com CPF ativa", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Chave PIX com CPF desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });


        switch5 = findViewById(R.id.switch5);
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Chave PIX com Telefone ativa", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Chave PIX com Telefone desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });



        switch4 = findViewById(R.id.switch4);
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Chave PIX com E-mail ativa", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Chave PIX com E-mail desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });


        etTel = findViewById(R.id.etTel);
        etCpf = findViewById(R.id.etCpf);
        etEmail = findViewById(R.id.etEmail);

        ib_voltar_pix_chaves = findViewById(R.id.ib_voltar_pix_chaves);
        ib_voltar_pix_chaves.setOnClickListener(v -> {
            if (pixActivity.equals("pixHome")){
                Intent intent = new Intent(getApplicationContext(),pix_menu.class);
                finish();
                startActivity(intent);
            }else if(pixActivity.equals("pixMenu")){
                Intent intent = new Intent(getApplicationContext(),Configurar_Pix.class);
                finish();
                startActivity(intent);
            }

        });


        btn_salvar_portabilidade_pix = findViewById(R.id.btn_salvar_portabilidade_pix);
        btn_salvar_portabilidade_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDadosUsuario();

            }
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

    private void configDados() {
        etCpf.setText(usuario.getPixCpf());
        etTel.setText(usuario.getPixTelefone());
        etEmail.setText(usuario.getPixEmail());

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperaDados();
    }

    private void salvarDadosUsuario(){

        usuario.setPixCpf(etCpf.getText().toString());
        usuario.setPixEmail(etEmail.getText().toString());
        usuario.setPixTelefone(etTel.getText().toString());



        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(usuario.getId());
        usuarioRef.setValue(usuario).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(this, "Chaves Pix cadastradas com sucesso!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Não foi possível salvar as informações.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}