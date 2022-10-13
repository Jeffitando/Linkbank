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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.Recarga;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class comprovante_recarga extends AppCompatActivity {

    private TextView textCodigo;
    private TextView textData, tvOperadora;
    private TextView textValor;
    private TextView textNumero;
    private String idOperadora;
    private ImageButton ib_voltar_recarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_recarga);

        tvOperadora = findViewById(R.id.tvOperadora);



        iniciaComponentes();

        getRecarga();

        ib_voltar_recarga = findViewById(R.id.ib_voltar_recarga);
        ib_voltar_recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRecarga = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentRecarga);
            }
        });



    }





    private void getRecarga(){
        String idRecarga = (String) getIntent().getSerializableExtra("idRecarga");
        idOperadora = (String) getIntent().getSerializableExtra("idOperadora");

        DatabaseReference recargaRef = FirebaseHelper.getDatabaseReference()
                .child("recargas")
                .child(idRecarga);

        recargaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Recarga recarga = snapshot.getValue(Recarga.class);
                configDados(recarga);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configDados(Recarga recarga){
        textCodigo.setText(recarga.getId());
        textData.setText(GetMask.getDate(recarga.getData(), 3));
        textValor.setText(getString(R.string.text_valor, GetMask.getValor(recarga.getValor())));
        textNumero.setText(recarga.getNumero());
        tvOperadora.setText(idOperadora);
    }




    private void iniciaComponentes(){
        textCodigo = findViewById(R.id.textCodigo);
        textData = findViewById(R.id.textData);
        textValor = findViewById(R.id.textValor);
        textNumero = findViewById(R.id.textNumero);
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

