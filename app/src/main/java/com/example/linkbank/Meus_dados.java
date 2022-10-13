package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class Meus_dados extends AppCompatActivity {

    private ImageButton ib_voltar_dados, ib_salvar;
    private Button btn_salvar_dados;
    private ImageView imagemPerfil;
    private Usuario usuario;
    private String imgUrlSup;

    private final int REQUEST_GALERIA = 100;

    private TextInputEditText edNome, edTelefone, edEmail, edRg, edNascimento, edCep, edRua, edBairro, edCidade, edEstado, edNumero, edComplemento, edProfissao, edRenda, edCpf;
    private Uri mSelectedUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        findViewById(R.id.btn_salvar_dados).setOnClickListener(v -> {
            salvarDadosUsuario();
            Intent intentSalvarBtn = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intentSalvarBtn);
        });

        ib_salvar = findViewById(R.id.ib_salvar_dados);
        ib_salvar.setOnClickListener(view -> {
            salvarDadosUsuario();
            Intent intentConfig = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intentConfig);

        });

        imagemPerfil = findViewById(R.id.imagemPerfil);
        imagemPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });

        ib_voltar_dados = findViewById(R.id.ib_voltar_dados);
        ib_voltar_dados.setOnClickListener(view -> {
            Intent intentConfig = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intentConfig);
        });

    }
    private void iniciaComponentes() {
        edNome = findViewById(R.id.EdNome);
        edEmail = findViewById(R.id.edEmail);
        edTelefone = findViewById(R.id.edtTelefone);
        imagemPerfil = findViewById(R.id.imagemPerfil);
        edRg = findViewById(R.id.edRg);
        edNascimento = findViewById(R.id.edNascimento);
        edCep = findViewById(R.id.edCep);
        edRua = findViewById(R.id.edRua);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edEstado = findViewById(R.id.edEstado);
        edNumero = findViewById(R.id.edNumero);
        edComplemento = findViewById(R.id.edComplemento);
        edProfissao = findViewById(R.id.edProfissao);
        edRenda = findViewById(R.id.edRenda);
        edCpf = findViewById(R.id.edtCpf);

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
        edNome.setText(usuario.getNome());
        edTelefone.setText(usuario.getTelefone());
        edEmail.setText(usuario.getEmail());
        edRg.setText(usuario.getRg());
        edNascimento.setText(usuario.getAniversario());
        edCep.setText(usuario.getCep());
        edRua.setText(usuario.getRua());
        edBairro.setText(usuario.getBairro());
        edCidade.setText(usuario.getCidade());
        edEstado.setText(usuario.getEstado());
        edNumero.setText(usuario.getNumero());
        edComplemento.setText(usuario.getComplemento());
        edProfissao.setText(usuario.getProfissao());
        edRenda.setText(usuario.getRenda());
        edCpf.setText(usuario.getCpf());


        if (usuario.getUrlImagem().equals("")) {
            return;
        }else{
            Picasso.get().load(usuario.getUrlImagem())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.user)
                    .into(imagemPerfil);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        iniciaComponentes();
        recuperaDados();
    }




    private void salvarDadosUsuario(){

        usuario.setNome(edNome.getText().toString());
        usuario.setEmail(edEmail.getText().toString());
        usuario.setRg(edRg.getText().toString());
        usuario.setComplemento(edComplemento.getText().toString());
        usuario.setNumero(edNumero.getText().toString());
        usuario.setRua(edRua.getText().toString());
        usuario.setBairro(edBairro.getText().toString());
        usuario.setEstado(edEstado.getText().toString());
        usuario.setCidade(edCidade.getText().toString());
        usuario.setCpf(edCpf.getText().toString());
        usuario.setProfissao(edProfissao.getText().toString());
        usuario.setRenda(edRenda.getText().toString());
        usuario.setAniversario(edNascimento.getText().toString());
        usuario.setTelefone(edTelefone.getText().toString());
        usuario.setCep(edCep.getText().toString());


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

    private void selectPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            mSelectedUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                imagemPerfil.setImageDrawable(new BitmapDrawable(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveImageFirebase();
        }
    }



//    private void saveUserFirebase(){
//        String filename = UUID.randomUUID().toString();
//        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/"+filename);
//        ref.putFile(mSelectedUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                Log.i("Teste", uri.toString());
//                                salvarDadosUsuario();
//                            }
//                        });
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("Teste", e.getMessage(), e);
//                    }
//                });
//    }

    private void saveImageFirebase(){
        String filename = UUID.randomUUID().toString();
        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/"+filename);
        ref.putFile(mSelectedUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("Teste", uri.toString());
                                imgUrlSup = uri.toString();
                                usuario.setUrlImagem(imgUrlSup);
                                salvarImagemUsuario();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Teste", e.getMessage(), e);
                    }
                });
    }

    private void salvarImagemUsuario(){

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

