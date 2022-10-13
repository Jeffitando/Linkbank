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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class comprovante_ted extends AppCompatActivity {


    Button share;
    private ImageButton ib_voltar_recarga;

    private TextView textData, textValor, textUsuario, textConta, textAgencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_ted);


        textData = findViewById(R.id.textData);
        textValor = findViewById(R.id.textValor);
        textUsuario = findViewById(R.id.textUsuario);
        textConta = findViewById(R.id.textConta);
        textAgencia = findViewById(R.id.textAgencia);
        textUsuario = findViewById(R.id.textUsuario);

        String data = (String) getIntent().getSerializableExtra("data_transfeerncia");
        String valor = (String) getIntent().getSerializableExtra("valor_transfeerncia");
        String conta = (String) getIntent().getSerializableExtra("numero_conta");
        String agencia = (String) getIntent().getSerializableExtra("numero_agencia");
        String nome = (String) getIntent().getSerializableExtra("nome_usuario");


        textData.setText(data);
        textValor.setText(valor);
        textConta.setText(conta);
        textAgencia.setText(agencia);
        textUsuario.setText(nome);

        ib_voltar_recarga = findViewById(R.id.ib_voltar_recarga);
        ib_voltar_recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentComprovanteTed = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentComprovanteTed);
            }
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