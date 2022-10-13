package com.example.linkbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.DepositoExtrato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DepositoReciboActivity extends AppCompatActivity {

    private TextView textCodigo;
    private TextView textData;
    private TextView textValor;
    private ImageButton ib_voltar_deposito;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito_recibo);
        iniciaComponentes();
        getDeposito();

        ib_voltar_deposito = findViewById(R.id.ib_voltar_deposito);
        ib_voltar_deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentVoltar);
            }
        });




    }

    private void getDeposito() {
        String idDeposito = (String) getIntent().getSerializableExtra("idDeposito");

        DatabaseReference depositoRf = FirebaseHelper.getDatabaseReference()
                .child("depositos")
                .child(idDeposito);
        depositoRf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DepositoExtrato deposito = snapshot.getValue(DepositoExtrato.class);
                configDados(deposito);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void configDados(DepositoExtrato deposito) {
        textCodigo.setText(deposito.getId());
        textData.setText(GetMask.getDate(deposito.getData(), 3));
        textValor.setText(getString(R.string.text_valor, GetMask.getValor(deposito.getValor())));
    }


    private void iniciaComponentes() {

        textCodigo = findViewById(R.id.textCodigo);
        textData = findViewById(R.id.textData);
        textValor = findViewById(R.id.textValor);

    }

    public void gerarPDF(View view) {
        PdfDocument documentoPDF = new PdfDocument();

        PdfDocument.PageInfo detalhesDaPagina =
                new PdfDocument.PageInfo.Builder(500, 600, 1).create();

        PdfDocument.Page novaPagina = documentoPDF.startPage(detalhesDaPagina);

        Canvas canvas = novaPagina.getCanvas();

        Paint corDoTexto = new Paint();
        corDoTexto.setColor(Color.BLUE);

        canvas.drawText("TESTE", 105, 100, corDoTexto);

        documentoPDF.finishPage(novaPagina);

        String targetPdf = "/sdcard/Download/comprovante.pdf";
        File filePath = new File(targetPdf);

        try {
            documentoPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Comprovante gerado com sucesso", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Selecione uma opção de compartilhamento", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        documentoPDF.close();

        final Uri arquivo = Uri.parse(targetPdf);
        final Intent _intent = new Intent();
        _intent.setAction(Intent.ACTION_SEND);
        _intent.putExtra(Intent.EXTRA_STREAM, arquivo);
        _intent.putExtra(Intent.EXTRA_TEXT, "");
        _intent.putExtra(Intent.EXTRA_TITLE, "Compartilhar comprovate.");

        _intent.setType("application/pdf");

        startActivity(Intent.createChooser(_intent, "Compartilhar"));

    }
}