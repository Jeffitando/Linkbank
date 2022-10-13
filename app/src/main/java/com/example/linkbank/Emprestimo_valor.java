package com.example.linkbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.DepositoExtrato;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Emprestimo_valor extends AppCompatActivity {

    private Button btnConfirmar;
    private CurrencyEditText edtValor;
    private ImageButton ib_voltar_emprestimos;
    private ImageView btn_ajuda;
    private EditText txt_saldoEscrito;
    private Button btnAvancar;
    private AlertDialog dialog;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimo_valor);

        recuperaUsuario();

        //Localizando as variaveis no código
        txt_saldoEscrito = findViewById(R.id.edtValorEmprestimo);
        btnAvancar = findViewById(R.id.btn_valor_emprestimo2);
        ib_voltar_emprestimos = findViewById(R.id.ib_voltar_emprestimos);
        btn_ajuda = findViewById(R.id.btn_ajuda);
        edtValor = findViewById(R.id.edtValorEmprestimo);

        aumentandoSaldo_mudaTela();
    }
    public void aumentandoSaldo_mudaTela() {

        btnAvancar.setOnClickListener(view -> {

            double valor = (double) edtValor.getRawValue() / 100;

            if(valor > 0 && valor < Integer.parseInt(usuario.getRenda())){

                ocultarTeclado();


                salvarExtrato(valor);


            }else {
                showDialog("Digite um valor maior que 0 e menor que seu Limite.");
            }
        });

        ib_voltar_emprestimos.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Gera_menu2.class);
            finish();
            startActivity(intent);

        });

        btn_ajuda.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Tela_ajuda_Emprestimo.class);
            finish();
            startActivity(intent);

        });


    }

    private void salvarExtrato(double valorDeposito){

        Extrato extrato = new Extrato();
        extrato.setOperacao("EMPRESTIMO");
        extrato.setValor(valorDeposito);
        extrato.setTipo("ENTRADA");

        DatabaseReference extratoRef = FirebaseHelper.getDatabaseReference()
                .child("extratos")
                .child(FirebaseHelper.getIdFirebase())
                .child(extrato.getId());
        extratoRef.setValue(extrato).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                DatabaseReference updateExtrato = extratoRef
                        .child("data");
                updateExtrato.setValue(ServerValue.TIMESTAMP);

                salvarDeposito(extrato);

            }else {
                showDialog("Não foi possível efetuar o deposito, tente mais tarde.");
            }
        });

    }

    private void salvarDeposito(Extrato extrato) {

        DepositoExtrato deposito = new DepositoExtrato();
        deposito.setId(extrato.getId());
        deposito.setValor(extrato.getValor());

        DatabaseReference depositoRef = FirebaseHelper.getDatabaseReference()
                .child("emprestimo")
                .child(deposito.getId());

        depositoRef.setValue(deposito).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                DatabaseReference updateDeposito = depositoRef
                        .child("data");
                updateDeposito.setValue(ServerValue.TIMESTAMP);
                usuario.setSaldo(usuario.getSaldo() + deposito.getValor());
                usuario.atualizarSaldo();

                showDialog("Empréstimo feito com sucesso!");

            }else {
                showDialog("Não foi possível efetuar o deposito, tente mais tarde.");
            }
        });

    }

    private void recuperaUsuario(){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(FirebaseHelper.getIdFirebase());
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
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


    private void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );

        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Atenção");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText(msg);

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

    private void configDados(){
        Button btnvalor = findViewById(R.id.btn_valor_emprestimo);
        btnvalor.setText("R$ "+ usuario.getRenda());

    }
}
