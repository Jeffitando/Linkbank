package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class comprovante_doc extends AppCompatActivity {


    private ImageButton ib_voltar_doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_doc);


        ib_voltar_doc = findViewById(R.id.ib_voltar_doc);
        ib_voltar_doc.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), transferencia_menu.class);
            finish();
            startActivity(intent);
        });


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