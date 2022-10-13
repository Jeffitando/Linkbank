package com.example.linkbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkbank.R;
import com.example.linkbank.model.ItemNotifier;

import java.util.List;

public class AdapterNotifier extends RecyclerView.Adapter<AdapterNotifier.MyViewHolder> {

    private List<ItemNotifier> listaItens;

    public AdapterNotifier(List<ItemNotifier> lista) {
        this.listaItens = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemlista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notifier, parent, false);

        return new MyViewHolder(itemlista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemNotifier item = listaItens.get(position);
        String msg = item.getMensagem();
        holder.mensagem.setText(msg);

    }

    @Override
    public int getItemCount() {
        return listaItens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mensagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mensagem = itemView.findViewById(R.id.tvMensagem);
        }
    }



}
