package com.example.linkbank.cobrar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.Home;
import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.model.Cobranca;
import com.example.linkbank.transferencia.SelecionarUsuarioActivity;

import java.util.Locale;

public class CobrarFormActivity extends AppCompatActivity {

    private CurrencyEditText edtValor;
    private AlertDialog dialog;
    private ImageButton ib_voltar_cobrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobrar_form);

        iniciaComponentes();



    }

    public void continuar(View view){

        double valor = (double) edtValor.getRawValue() / 100;

        if(valor >= 10){

            Cobranca cobranca = new Cobranca();
            cobranca.setIdEmitente(FirebaseHelper.getIdFirebase());
            cobranca.setValor(valor);

            Intent intent = new Intent(this, SelecionarUsuarioActivity.class);
            intent.putExtra("cobranca", cobranca);
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
        mensagem.setText("Valor mínimo para cobrança deve ser maior ou igual a R$ 10,00");

        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }



    private void iniciaComponentes(){
        edtValor = findViewById(R.id.edtValor);
        edtValor.setLocale(new Locale("PT", "br"));

        findViewById(R.id.ib_voltar_cobrar).setOnClickListener( v -> {
            finish();
            startActivity(new Intent(this, Home.class));

        } );
    }

}