package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.DepositoExtrato;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.PagamentoExtratoExemplo;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class Dados_boleto extends AppCompatActivity {



    private Button pagar_boleto;
    private ImageButton ib_voltar_boletos;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_boleto);

        recuperaUsuario();

        pagar_boleto = findViewById(R.id.pagar_boleto);
        pagar_boleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarExtrato();

            }
        });

        ib_voltar_boletos = findViewById(R.id.ib_digitar_codigos);
        ib_voltar_boletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(),Digitar_codigo_barras.class);
                finish();
                startActivity(intent3);
            }
        });

    }
    private void salvarExtrato(){

        if (usuario.getSaldo()<593){
            Toast.makeText(this, "Sem Saldo", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Extrato extrato = new Extrato();
            extrato.setOperacao("PAGAMENTO");
            extrato.setValor(593);
            extrato.setTipo("SAIDA");

            DatabaseReference extratoRef = FirebaseHelper.getDatabaseReference()
                    .child("extratos")
                    .child(FirebaseHelper.getIdFirebase())
                    .child(extrato.getId());
            extratoRef.setValue(extrato).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    DatabaseReference updateExtrato = extratoRef
                            .child("data");
                    updateExtrato.setValue(ServerValue.TIMESTAMP);

                    salvarPagamento(extrato);

                }
            });
            Intent intent = new Intent(getApplicationContext(),Comprovante_boleto.class);
            finish();
            startActivity(intent);
        }

    }

    private void salvarPagamento(Extrato extrato) {

        PagamentoExtratoExemplo pagamento = new PagamentoExtratoExemplo();
        pagamento.setId(extrato.getId());
        pagamento.setValor(extrato.getValor());

        DatabaseReference pagamentoRef = FirebaseHelper.getDatabaseReference()
                .child("pagamentoqr")
                .child(pagamento.getId());

        pagamentoRef.setValue(pagamento).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                DatabaseReference updatePagamento = pagamentoRef
                        .child("data");
                updatePagamento.setValue(ServerValue.TIMESTAMP);
                usuario.setSaldo(usuario.getSaldo() - pagamento.getValor());
                usuario.atualizarSaldo();

                Intent intent = new Intent(this, Comprovante_boleto.class);
                intent.putExtra("idPagamento", pagamento.getId());
                startActivity(intent);
                finish();

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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}