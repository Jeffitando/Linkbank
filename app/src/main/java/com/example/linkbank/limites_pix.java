package com.example.linkbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class limites_pix extends AppCompatActivity {

    private Button btn_pix_mensal, btn_btn_pix_semanal, btn_pix_diario, btn_pix_agendado, btn_pix_perosonalizar;
    private ImageButton ib_voltar_pix_limites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limites_pix);


        btn_pix_perosonalizar = findViewById(R.id.btn_pix_perosonalizar);
        btn_pix_perosonalizar.setOnClickListener(v -> {
            Intent intent5 = new Intent(getApplicationContext(), aumentar_limite_pix.class);
            finish();
            startActivity(intent5);
        });


        ib_voltar_pix_limites = findViewById(R.id.ib_voltar_pix_limites);
        ib_voltar_pix_limites.setOnClickListener(v -> {
            Intent intent4 = new Intent(getApplicationContext(), Configurar_Pix.class);
            finish();
            startActivity(intent4);
        });


        btn_pix_mensal = findViewById(R.id.btn_pix_mensal);
        btn_pix_mensal.setOnClickListener(v -> showMensalAlertDialog());

    }

    private void showMensalAlertDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(limites_pix.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(limites_pix.this).inflate(
                R.layout.layout_pix_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Informações");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Seu limite mensal de transfência Pix é de R$ 4.000,000");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_ok).setOnClickListener(v -> alertDialog.dismiss());


        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


        btn_btn_pix_semanal = findViewById(R.id.btn_btn_pix_semanal);
        btn_btn_pix_semanal.setOnClickListener(v -> showSemanalAlertDialog());


    }

    private void showSemanalAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(limites_pix.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(limites_pix.this).inflate(
                R.layout.layout_pix_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Informações");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Seu limite semanal de transfência Pix é de R$ 2.000,000");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_ok).setOnClickListener(v -> alertDialog.dismiss());


        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


        btn_pix_diario = findViewById(R.id.btn_pix_diario);
        btn_pix_diario.setOnClickListener(v -> showDiariolAlertDialog());


    }

    private void showDiariolAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(limites_pix.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(limites_pix.this).inflate(
                R.layout.layout_pix_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Informações");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Seu limite diário de transfência Pix é de R$ 800,000");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_ok).setOnClickListener(v -> alertDialog.dismiss());


        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        btn_pix_agendado = findViewById(R.id.btn_pix_agendado);
        btn_pix_agendado.setOnClickListener(v -> showAgendadoAlertDialog());

    }

    private void showAgendadoAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(limites_pix.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(limites_pix.this).inflate(
                R.layout.layout_pix_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Informações");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Seu limite de Pix por agendamento é de R$ 8.000,000. Sendo liberado após 7 dias da solicitação");
        //((Button) view.findViewById(R.id.buttonYes)).setText("Habilitar");
        //((Button) view.findViewById(R.id.buttonNo)).setText("Desabilitar");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_ok).setOnClickListener(v -> alertDialog.dismiss());


        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();


    }


}


