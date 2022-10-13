package com.example.linkbank.transferencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.example.linkbank.transferencia_menu;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.santalu.maskara.widget.MaskEditText;

import java.util.ArrayList;
import java.util.List;

public class Transferir_ted extends AppCompatActivity {


    private ImageButton ib_voltar_transferencia;
    private ImageView iv_favoritos;
    private MaskEditText NumeroConta, numeroAgencia;
    private TextInputEditText etNome, etNumeroConta, etBanco, etNumeroAgencia, etCpf;
    private String docTed;
    private TextView tvBemVindo;
    private AlertDialog dialog;
    private AlertDialog dialog2;
    private final List<Usuario> usuarioList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_ted);

        iniciaComponentes();
        recuperarUsuarios();

        getExtra();

        configDados();






        iv_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFavoritos = new Intent(getApplicationContext(), Valor_transferencia.class);
                finish();
                startActivity(intentFavoritos);
            }
        });


        ib_voltar_transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarHomeTed = new Intent(getApplicationContext(), transferencia_menu.class);
                finish();
                startActivity(intentVoltarHomeTed);
            }
        });

     findViewById(R.id.avancar_ted).setOnClickListener(view -> {

        validaDados();


     });

    }









    private void validaDados(){
        String banco = etBanco.getText().toString();
        String nome = etNome.getText().toString();
        String numeroAg = etNumeroAgencia.getText().toString();
        String numeroC = etNumeroConta.getText().toString();
        String cpf = etCpf.getText().toString();

        cpf.replaceAll(" ", "");
        cpf = " "+cpf+" ";

        if (!banco.isEmpty()){
            if (!nome.isEmpty()){
                if (!numeroAg.isEmpty()){
                    if (!numeroC.isEmpty()){
                        if (!cpf.isEmpty()){

                            boolean achou = false;

                            for ( int i = 0; i < usuarioList.size(); i++){
                                if (usuarioList.get(i).getCpf().equals(cpf)){

                                    Usuario usuario = usuarioList.get(i);

                                    String idUsuario = usuario.getId();

                                    Transferencia transferencia = new Transferencia();
                                    transferencia.setIdUserOrigem(FirebaseHelper.getIdFirebase());
                                    transferencia.setIdUserDestino(idUsuario);

                                    Intent intent = new Intent(this, ValorTedActivity.class);
                                    intent.putExtra("transferencia", transferencia);
                                    intent.putExtra("usuario", usuario);
                                    startActivity(intent);

                                    achou = true;
                                }
                            }if (!achou){showDialog2();}
                        }else{showDialog();}
                    }else{showDialog();}
                }else{showDialog();}
            }else{showDialog();}
        }else{
            showDialog();
        }


    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );



        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Atenção");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText("Preencha todos os campos");



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
        mensagem.setText("CPF Inválido.");



        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

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
                            Toast.makeText(Transferir_ted.this, "Nenhum usuário cadastrado no banco", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void iniciaComponentes() {
        tvBemVindo = findViewById(R.id.tvBemVindo);
        etBanco = findViewById(R.id.etBanco);
        etNumeroConta = findViewById(R.id.etNumeroConta);
        etNumeroAgencia = findViewById(R.id.etNumeroAgencia);
        etCpf = findViewById(R.id.etCpf);
        etNome = findViewById(R.id.etNome);
        iv_favoritos = findViewById(R.id.iv_favoritos);
        ib_voltar_transferencia = findViewById(R.id.ib_voltar_transferencia);
    }

    private void configDados(){
        if (docTed.equals("doc")){
            tvBemVindo.setText("Olá. Seja Bem-Vindo a área de transferência DOC");
        }else if (docTed.equals("ted")){
            tvBemVindo.setText("Olá. Seja Bem-Vindo a área de transferência TED");
        }
    }

    private void getExtra() {
        docTed = getIntent().getStringExtra("tipo");
    }
}