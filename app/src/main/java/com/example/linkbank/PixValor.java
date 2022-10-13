package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.PixTransacao;
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.example.linkbank.transferencia.TransferenciaConfirmaActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.santalu.maskara.widget.MaskEditText;

import java.util.Locale;



public class PixValor extends AppCompatActivity {

    private CurrencyEditText edtValor;
    private ImageButton ib_voltar_ted;
    private ProgressBar progressBar2;

    private PixTransacao pixTransacao;
    private Usuario usuario;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_valor);
        getExtra();
        iniciaComponentes();

        ib_voltar_ted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Pix.class);
                finish();
                startActivity(intent);
            }
        });

        findViewById(R.id.avancar_pix).setOnClickListener(view -> {

            double valor = (double) edtValor.getRawValue() / 100;
            pixTransacao.setValor(valor);



            Intent intent = new Intent(this, PixConfirma.class);
            intent.putExtra("pix", pixTransacao);
            intent.putExtra("usuario", usuario);
            finish();
            startActivity(intent);

        });

    }
    private void getExtra() {
        pixTransacao = (PixTransacao) getIntent().getSerializableExtra("pix");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
    }

    private void iniciaComponentes(){
        edtValor = findViewById(R.id.edtValor);
        edtValor.setLocale(new Locale("pt", "BR"));
        ib_voltar_ted = findViewById(R.id.ib_voltarPix);
    }

}