package com.example.linkbank.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkbank.R;
import com.example.linkbank.adapter.helper.GetMask;
import com.example.linkbank.model.Extrato;
import com.example.linkbank.model.ItemExtrato;

import java.util.List;


public class ExtratoAdapter extends RecyclerView.Adapter<ExtratoAdapter.MyViewHolder> {

    private final List<Extrato> extratoList;
    private final Context context;

    public ExtratoAdapter(List<Extrato> extratoList, Context context) {
        this.extratoList = extratoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_extrato, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        Extrato extrato = extratoList.get(position);

        String icon = extrato.getOperacao().substring(0, 1);
        holder.textIcon.setText(icon);
        holder.textOperacao.setText(extrato.getOperacao());
        holder.textData.setText(GetMask.getDate(extrato.getData(), 3));
        holder.textValor.setText(context.getString(R.string.text_valor, GetMask.getValor(extrato.getValor())));

        switch (extrato.getOperacao()) {
            case "DEPOSITO":
                holder.textIcon.setBackgroundResource(R.drawable.bg_entrada);
                break;
            case "RECARGA":
                holder.textIcon.setBackgroundResource(R.drawable.bg_saida);
                break;

            case "TRANSFERENCIA":
            case "PIX":
            case "PAGAMENTO":
                if (extrato.getTipo().equals("ENTRADA")) {
                    holder.textIcon.setBackgroundResource(R.drawable.bg_entrada);
                } else {
                    holder.textIcon.setBackgroundResource(R.drawable.bg_saida);
                }
                break;
        }

        holder.itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return extratoList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textIcon, textOperacao, textData, textValor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textIcon = itemView.findViewById(R.id.textIcon);
            textOperacao = itemView.findViewById(R.id.textOperacao);
            textData = itemView.findViewById(R.id.textData);
            textValor = itemView.findViewById(R.id.textValor);
        }
    }

}
