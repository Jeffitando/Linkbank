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
import android.widget.SeekBar;
import android.widget.TextView;

public class aumentar_limite_pix extends AppCompatActivity {

    private ImageButton ib_volta_aumentar_limite_pix;
    private Button btn_enviar_limite_pix;
    private SeekBar seekBar3, seekBar4, seekBar5, seekBar6;
    private TextView textView117, textView120, textView122, textView124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aumentar_limite_pix);


        seekBar3 = findViewById(R.id.seekBar3);
        seekBar4 = findViewById(R.id.seekBar4);
        seekBar5 = findViewById(R.id.seekBar5);
        seekBar6 = findViewById(R.id.seekBar6);
        textView120 = findViewById(R.id.textView120);
        textView117 = findViewById(R.id.textView117);
        textView122 = findViewById(R.id.textView122);
        textView124 = findViewById(R.id.textView124);

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView117.setText("R$ " + "" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView117.setText("R$8.000,00");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        ib_volta_aumentar_limite_pix = findViewById(R.id.ib_volta_aumentar_limite_pix);
        ib_volta_aumentar_limite_pix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Configurar_Pix.class);
            finish();
            startActivity(intent);
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView120.setText("R$ " + "" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView120.setText("R$2.000,00");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView122.setText("R$ " + "" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView122.setText("R$800,00");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView124.setText("R$ " + "" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView124.setText("R$8.000,00");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btn_enviar_limite_pix = findViewById(R.id.btn_enviar_limite_pix);
        btn_enviar_limite_pix.setOnClickListener(v -> showEnviarPixAlertDialog());

    }

    private void showEnviarPixAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(aumentar_limite_pix.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(aumentar_limite_pix.this).inflate(
                R.layout.layout_pix_background, (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText("Informações");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Sua solicitação de alteração de limite de pix foi enviada com sucesso! em breve o banco entrara em contato");
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