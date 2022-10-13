package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.PixTransacao;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Telefone_pix extends AppCompatActivity {

    private Button avancar_pix;
    private ImageButton duvida_pix;
    private ImageButton ib_voltarPix;
    private CurrencyEditText edtValor;
    private String tipoPix;
    private EditText etDat;
    private AlertDialog dialog;
    private PixTransacao pix;
    private Usuario usuarioDestino;
    private Usuario usuarioOrigem;
    private final List<Usuario> usuarioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefone_pix);


        recuperarUsuarios();
        etDat = findViewById(R.id.etDat);
        edtValor = findViewById(R.id.edtValor);





        ib_voltarPix = findViewById(R.id.ib_voltarPix);
        ib_voltarPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Pix.class);
                finish();
                startActivity(intentVoltar);
            }
        });

        duvida_pix = findViewById(R.id.duvida_pix);
        duvida_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDuvida = new Intent(getApplicationContext(), com.example.linkbank.duvida_pix.class);
                finish();
                startActivity(intentDuvida);
            }
        });



        avancar_pix = findViewById(R.id.avancar_pix);
        avancar_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaDados();
            }
        });

    }
    private void recuperarUsuarios() {
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario");
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    usuarioList.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        Usuario usuario = ds.getValue(Usuario.class);

                        if (usuario != null) {

                            usuarioList.add(usuario);

                        }else{
                            Toast.makeText(Telefone_pix.this, "Nenhum usuário cadastrado no banco", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );



        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Atenção");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText("Preencha o campo.");



        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }

    private void showDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );



        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Atenção");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText("Usuário não encontrado.");



        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }

    private void validaDados(){
        String tel = etDat.getText().toString();
        //Toast.makeText(this, usuarioList.get(6).getPixCpf(), Toast.LENGTH_SHORT).show();

        if (!tel.isEmpty()){

            boolean achou = false;
            for ( int i = 0; i < usuarioList.size(); i++){
                if (usuarioList.get(i).getPixTelefone().equals(tel)){

                    Usuario usuario = usuarioList.get(i);

                    String idUsuario = usuario.getId();

                    PixTransacao pixTransacao = new PixTransacao();
                    pixTransacao.setIdUserOrigem(FirebaseHelper.getIdFirebase());
                    pixTransacao.setIdUserDestino(idUsuario);

                    Intent intent = new Intent(this, PixValor.class);
                    intent.putExtra("pix", pixTransacao);
                    intent.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent);

                    achou = true;
                }
            }if (!achou){showDialog2();}
        }else{showDialog();}


    }

}