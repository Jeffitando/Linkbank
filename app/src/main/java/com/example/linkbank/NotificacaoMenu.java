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
import com.example.linkbank.model.Usuario;
import com.example.linkbank.notificacoes.NotificacoesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NotificacaoMenu extends AppCompatActivity {


    private ImageButton ib_voltar_notificacao;
    private ImageView editNum, editEmail;
    private EditText etNum, etEmail;
    private boolean numSup, emailSup;
    private Button btnNotificacao, btnSalvar;
    private Switch switch5;
    private  Switch switch3;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);


        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDadosUsuario();
                Toast.makeText(NotificacaoMenu.this, "Dados Salvos", Toast.LENGTH_SHORT).show();
            }
        });

        btnNotificacao = findViewById(R.id.btnNotificacao);
        btnNotificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NotificacoesActivity.class);
                finish();
                startActivity(intent);
            }
        });


        switch3 = findViewById(R.id.switch3);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "O recebimento de notificações pelo e-mail esta ativado", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "O recebimento de notifações pelo e-mail esta desativado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch5 = findViewById(R.id.switch5);
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  Toast.makeText(getApplicationContext(), "O recebimento de notificações por SMS esta ativado", Toast.LENGTH_SHORT).show();
              }

              else {
                  Toast.makeText(getApplicationContext(), "O recebimento de notifações por SMS esta desativado", Toast.LENGTH_SHORT).show();
              }
          }
      });

        ib_voltar_notificacao = findViewById(R.id.ib_voltar_notificacao);
        ib_voltar_notificacao.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
            startActivity(intent);
            finish();
        });

        etNum = findViewById(R.id.etNum);
        etEmail = findViewById(R.id.etEmail);

        editNum = findViewById(R.id.imgEditNum);
        editEmail = findViewById(R.id.imgEditEmail);

        editNum.setOnClickListener(view -> {
            if (numSup) {
                etNum.setEnabled(false);
                etNum.clearFocus();
                numSup = false;

                etEmail.setEnabled(false);
                etEmail.clearFocus();
                emailSup = false;
            } else {
                etNum.setEnabled(true);
                etNum.requestFocus();
                numSup = true;

                etEmail.setEnabled(false);
                etEmail.clearFocus();
                emailSup = false;
            }
        });

        editEmail.setOnClickListener(view -> {
            if (emailSup) {
                etNum.setEnabled(false);
                etNum.clearFocus();
                numSup = false;

                etEmail.setEnabled(false);
                etEmail.clearFocus();
                emailSup = false;
            } else {
                etNum.setEnabled(false);
                etNum.requestFocus();
                numSup = false;

                etEmail.setEnabled(true);
                etEmail.clearFocus();
                emailSup = true;
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
        etNum.setText(usuario.getTelefone());
        etEmail.setText(usuario.getEmail());

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperaDados();
    }




    private void salvarDadosUsuario(){

        usuario.setEmail(etEmail.getText().toString());
        usuario.setTelefone(etNum.getText().toString());



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
