package com.example.linkbank.transferencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.Home;
import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Valor_transferencia extends AppCompatActivity {

    private CurrencyEditText edtValor;
    private AlertDialog dialog;
    private ImageButton ib_voltar_ted;

    private TextView tvSaldoConta;

    private Transferencia transferencia;
    private Usuario usuario;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_transferencia);

        ib_voltar_ted = findViewById(R.id.ib_voltar_ted);
        ib_voltar_ted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentVoltar);
            }
        });


        iniciaComponentes();

    }


    public void validaDados(View view){
        double valor = (double) edtValor.getRawValue() / 100;

        if(valor > 0){

            ocultarTeclado();

            Transferencia transferencia = new Transferencia();
            transferencia.setIdUserOrigem(FirebaseHelper.getIdFirebase());
            transferencia.setValor(valor);

            Intent intent = new Intent(this, SelecionarUsuarioActivity.class);
            intent.putExtra("transferencia", transferencia);
            startActivity(intent);

        }else {
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
        mensagem.setText("Digite um valor maior que 0.");

        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }

    // Oculta o teclado do dispositivo
    private void ocultarTeclado() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(edtValor.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void iniciaComponentes(){
        tvSaldoConta = findViewById(R.id.tvSaldo);
        edtValor = findViewById(R.id.edtValor);
        edtValor.setLocale(new Locale("pt", "BR"));
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