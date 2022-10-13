package com.example.linkbank;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cobranca_pix extends AppCompatActivity {

    CurrencyEditText etData;
    Button generate;
    ImageView qrcode;
    ImageButton ib_cobranca_pix;
    ConstraintLayout salvarDados;
    ConstraintLayout layoutCopiarCodigo;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobranca_pix);

        ib_cobranca_pix = findViewById(R.id.ib_cobranca_pix);
        ib_cobranca_pix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarQr = new Intent(getApplicationContext(), pix_menu.class);
                finish();
                startActivity(intentVoltarQr);
            }
        });



        etData = findViewById(R.id.etData);

        qrcode = findViewById(R.id.qrcode);
        generate = findViewById(R.id.generate);


     generate.setOnClickListener(v -> {
         double valor = (double) etData.getRawValue() / 100;
         String valorString = String.valueOf(valor);

         if (valor>0){
             MultiFormatWriter mWriter = new MultiFormatWriter();

             try {
                 BitMatrix mMatrix = mWriter.encode(valorString, BarcodeFormat.QR_CODE, 350, 350);

                 BarcodeEncoder mEncoder = new BarcodeEncoder();

                 Bitmap mBitmap = mEncoder.createBitmap(mMatrix);

                 qrcode.setImageBitmap(mBitmap);

                 InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                 manager.hideSoftInputFromWindow(etData.getApplicationWindowToken(), 0 );


             } catch (WriterException e) {
                 e.printStackTrace();
             }
         }else{showDialog();}





     });




    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog
        );



        View view = getLayoutInflater().inflate(R.layout.layout_dialog_info, null);

        TextView textTitulo = view.findViewById(R.id.textTitulo);
        textTitulo.setText("Atenção");

        TextView mensagem = view.findViewById(R.id.textMensagem);
        mensagem.setText("O valor tem que ser maior que R$ 0,00");



        Button btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(v -> dialog.dismiss());

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

    }

    public void gerarPDF(View view){
        PdfDocument documentoPDF = new PdfDocument();

        PdfDocument.PageInfo detalhesDaPagina =
                new PdfDocument.PageInfo.Builder(500,600,1).create();

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
        }  catch (IOException e) {
            Toast.makeText(this, "Selecione uma opção de compartilhamento", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        documentoPDF.close();

        final Uri arquivo = Uri.parse(targetPdf);
        final Intent _intent = new Intent();
        _intent.setAction(Intent.ACTION_SEND);
        _intent.putExtra(Intent.EXTRA_STREAM,  arquivo);
        _intent.putExtra(Intent.EXTRA_TEXT,  "");
        _intent.putExtra(Intent.EXTRA_TITLE,   "Compartilhar comprovate.");

        _intent.setType("application/pdf");

        startActivity(Intent.createChooser(_intent, "Compartilhar"));


        salvarDados = findViewById(R.id.salvarDados);
        salvarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Download dos arquivos realizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });


        layoutCopiarCodigo = findViewById(R.id.layoutCopiarCodigo);
        layoutCopiarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "código copiado com sucesso", Toast.LENGTH_SHORT).show();

            }
        });


    }




}
