package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.linkbank.adapter.AdapterNotifier;
import com.example.linkbank.model.ItemNotifier;

import java.util.ArrayList;
import java.util.List;

public class NotificacaoLista extends AppCompatActivity {

    private ImageButton btnVoltar, btnConfig;
    private RecyclerView recyclerNotifier;
    private List<ItemNotifier> listaItens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao_lista);

        //BOTOES

        btnVoltar = findViewById(R.id.btnVoltar);
        btnConfig = findViewById(R.id.btnConfig);

        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        });

        btnConfig.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NotificacaoMenu.class);
            intent.putExtra("chave_notificacao", "NotificacaoLista");
            startActivity(intent);
            finish();
        });
        // FIM BOTOES


        //RECYCLER
        recyclerNotifier = findViewById(R.id.recyclerNotifier);

        this.criarMsg();

        AdapterNotifier adapter = new AdapterNotifier(listaItens);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerNotifier.setLayoutManager(layoutManager);
        recyclerNotifier.setAdapter(adapter);
        recyclerNotifier.setHasFixedSize(true);
        recyclerNotifier.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


    }

    public void criarMsg() {
        ItemNotifier msg = new ItemNotifier("Transação Pix recebida de R$ 39,00; De: Joao");
        this.listaItens.add(msg);

        msg = new ItemNotifier("Transação Pix enviada de R$ 100,00; Para: Ruan");
        this.listaItens.add(msg);

        msg = new ItemNotifier("Pagamento de boleto no valor de R$ 120,00");
        this.listaItens.add(msg);

        msg = new ItemNotifier("Sua fatura vence amanhã. Faça o pagamento para liberar seu limite");
        this.listaItens.add(msg);

        msg = new ItemNotifier("Transação Pix recebida de R$ 250,00; De: Carol");
        this.listaItens.add(msg);

        msg = new ItemNotifier("Verifique se tem crédito para fazer um empréstimo!");
        this.listaItens.add(msg);

        msg = new ItemNotifier("Pagamentos ficam práticos e seguros com carteiras digitais.");
        this.listaItens.add(msg);
    }
}