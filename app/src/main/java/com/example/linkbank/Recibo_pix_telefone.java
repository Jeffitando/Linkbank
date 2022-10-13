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
import com.example.linkbank.model.Transferencia;
import com.example.linkbank.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Recibo_pix_telefone extends AppCompatActivity {

    private ImageButton ib_voltar_recarga;
    private TextView textCodigo;
    private TextView textUsuario;
    private TextView textData;
    private TextView textValor;
    private TextView textTipoTransferencia;
    private TextView textInfoTransferencia;
    private ImageView imagemUsuario;
    private ImageButton ib_voltar_seguros_comprovante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_pix_telefone);


        ib_voltar_recarga = findViewById(R.id.ib_voltar_seguros_comprovante);
        ib_voltar_recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intent);
            }
        });

        iniciaComponentes();

        recuperaTransferencia();


    }


    private void recuperaTransferencia(){
        String idPix = getIntent().getStringExtra("idPix");

        DatabaseReference transferenciaRef = FirebaseHelper.getDatabaseReference()
                .child("pix")
                .child(idPix);
        transferenciaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Transferencia transferencia = snapshot.getValue(Transferencia.class);
                if(transferencia != null){


                    if(transferencia.getIdUserDestino().equals(FirebaseHelper.getIdFirebase())){
                        recuperaUsuario(transferencia, FirebaseHelper.getIdFirebase());
                    }else {
                        recuperaUsuario(transferencia, transferencia.getIdUserDestino());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void recuperaUsuario(Transferencia transferencia,String idUsuarioDestino ){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(idUsuarioDestino);
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                if(usuario != null){
                    configDados(transferencia, usuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configDados(Transferencia transferencia, Usuario usuario){
        textCodigo.setText(transferencia.getId());
        textData.setText(GetMask.getDate(transferencia.getData(), 3));
        textValor.setText(getString(R.string.text_valor, GetMask.getValor(transferencia.getValor())));


        if(usuario.getId().equals(FirebaseHelper.getIdFirebase())){
            textTipoTransferencia.setText(getString(R.string.text_tipo_transferencia, "Recebida"));
            textInfoTransferencia.setText("O valor recebido via transferência já foi adicionado ao saldo da conta.");
        }else {
            textTipoTransferencia.setText(getString(R.string.text_tipo_transferencia, "Enviada"));
            textInfoTransferencia.setText("Débito realizado com sucesso. A previsão do crédito na conta de destino é de até 30 minutos.");
        }

        if(usuario.getUrlImagem() != null){
            Picasso.get().load(usuario.getUrlImagem())
                    .placeholder(R.drawable.loading)
                    .into(imagemUsuario);
        }
        textUsuario.setText(usuario.getNome());
    }

    private void iniciaComponentes() {
        textCodigo = findViewById(R.id.textCodigo);
        textUsuario = findViewById(R.id.textUsuario);
        textData = findViewById(R.id.textData);
        textValor = findViewById(R.id.textValor);
        textTipoTransferencia = findViewById(R.id.textTipoTransferencia);
        textInfoTransferencia = findViewById(R.id.textInfoTransferencia);
        imagemUsuario = findViewById(R.id.imagemUsuario);
        ib_voltar_seguros_comprovante = findViewById(R.id.ib_voltar_seguros_comprovante);
        ib_voltar_seguros_comprovante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), Home.class);
                finish();
                startActivity(intentVoltar);
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