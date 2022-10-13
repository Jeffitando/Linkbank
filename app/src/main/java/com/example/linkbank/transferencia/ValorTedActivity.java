package com.example.linkbank.transferencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.Home;
import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.Cobranca;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.santalu.maskara.widget.MaskEditText;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class ValorTedActivity extends AppCompatActivity {


    private CurrencyEditText edtValor;
    private MaskEditText etData;
    private ImageButton ib_voltar_ted;
    private ProgressBar progressBar2;

    private TextView NumeroConta, numeroAgencia, tvSaldoConta;
    private  TextInputEditText Nome;

    private Transferencia transferencia;
    private Usuario usuario;
    private Usuario user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_ted);

        iniciaComponentes();
        getExtra();


        ib_voltar_ted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentVoltar);
            }
        });







        findViewById(R.id.btnConfirmar).setOnClickListener(view -> {

            double valor = (double) edtValor.getRawValue() / 100;
            transferencia.setValor(valor);

            Intent intent = new Intent(this, TransferenciaConfirmaActivity.class);
            intent.putExtra("transferencia", transferencia);
            intent.putExtra("usuario", usuario);
            startActivity(intent);



//            Intent intent = new Intent(this, ConfirmarTedActivity.class);
//
//            intent.putExtra("data_transfeerncia", data);
//            intent.putExtra("valor_transfeerncia", valor);
//            intent.putExtra("numero_conta", conta);
//            intent.putExtra("numero_agencia", agencia);
//            intent.putExtra("nome_usuario", nome);
//            startActivity(intent);


        });


    }
    private void getExtra() {
            transferencia = (Transferencia) getIntent().getSerializableExtra("transferencia");
            usuario = (Usuario) getIntent().getSerializableExtra("usuario");
    }

    private void iniciaComponentes(){
        tvSaldoConta = findViewById(R.id.tvSaldo);
        edtValor = findViewById(R.id.edtValor);
        edtValor.setLocale(new Locale("pt", "BR"));
        ib_voltar_ted = findViewById(R.id.ib_voltar_ted);
        progressBar2 = findViewById(R.id.progressBar2);
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