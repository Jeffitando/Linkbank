package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;

import com.example.linkbank.Home;
import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.Recarga;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.santalu.maskara.widget.MaskEditText;

import java.util.Locale;

public class recarga_celular extends AppCompatActivity {



    private CurrencyEditText edtValor;
    private MaskEditText edtTelefone;
    private AlertDialog dialog;
    private ProgressBar progressBar;
    private ConstraintLayout oi, vivo, tim, claro;

    private Usuario usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_celular);

        iniciaComponentes();
        recuperaUsuario();


        oi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaDados("oi");
            }
        });
        vivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaDados("vivo");
            }
        });
        claro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaDados("claro");
            }
        });
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaDados("tim");
            }
        });
    }

    public void validaDados(String operadora){

        String idOperadora = operadora;

        double valor = (double) edtValor.getRawValue() / 100;
      String numero = edtTelefone.getText().toString().trim();

        if(valor >= 15){
            if(!numero.isEmpty()){
                if(numero.length() == 14){
                    if(usuario != null){
                        if(usuario.getSaldo() >= valor){

                            progressBar.setVisibility(View.VISIBLE);

                            salvarExtrato(valor, numero, idOperadora);

                        }else {
                            showDialog("Saldo insuficiente.");
                        }
                    }else {
                        showDialog("Aguarde, ainda estamos recuperando as informa????es.");
                    }
                }else {
                    showDialog("O n??mero digitado est?? incompleto.");
                }
            }else {
                showDialog("Informe o n??mero.");
            }
        }else {
            showDialog("Recarga m??nima de R$ 15,00.");
        }

    }

    private void recuperaUsuario() {
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(FirebaseHelper.getIdFirebase());
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );

        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Aten????o");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText(msg);

        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }

    private void salvarRecarga(Extrato extrato, String numero, String operadora) {

        Recarga recarga = new Recarga();
        recarga.setId(extrato.getId());
        recarga.setNumero(numero);
        recarga.setValor(extrato.getValor());

        DatabaseReference recargaRef = FirebaseHelper.getDatabaseReference()
                .child("recargas")
                .child(recarga.getId());

        recargaRef.setValue(recarga).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                usuario.setSaldo(usuario.getSaldo() - extrato.getValor());
                usuario.atualizarSaldo();

                DatabaseReference updateRecarga = recargaRef
                        .child("data");
                updateRecarga.setValue(ServerValue.TIMESTAMP);

                Intent intent = new Intent(this, comprovante_recarga.class);
                intent.putExtra("idRecarga", recarga.getId());
                intent.putExtra("idOperadora", operadora);
                startActivity(intent);
                finish();

            }else {
                progressBar.setVisibility(View.GONE);
                showDialog("N??o foi poss??vel efetuar a recarga, tente mais tarde.");
            }
        });

    }

    private void salvarExtrato(double valor, String numero, String operadora){

        Extrato extrato = new Extrato();
        extrato.setOperacao("RECARGA");
        extrato.setValor(valor);
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

                salvarRecarga(extrato, numero, operadora);

            }else {
                showDialog("N??o foi poss??vel efetuar o deposito, tente mais tarde.");
            }
        });

    }



    private void iniciaComponentes(){
        edtValor = findViewById(R.id.edtValor);
        edtValor.setLocale(new Locale("PT", "br"));

        edtTelefone = findViewById(R.id.edtTelefone);

        progressBar = findViewById(R.id.progressBar);

        oi = findViewById(R.id.OperadoraOi);
        claro = findViewById(R.id.OperadoraClaro);
        tim = findViewById(R.id.OperadoraTim);
        vivo = findViewById(R.id.OperadoraVivo);
    }

    // Oculta o teclado do dispositivo
    private void ocultarTeclado() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(edtValor.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}