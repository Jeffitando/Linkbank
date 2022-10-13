package com.example.linkbank.adapter;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.linkbank.R;
import com.example.linkbank.model.ItemExtrato;
import java.util.List;
public class AdapterExtrato extends RecyclerView.Adapter<AdapterExtrato.MyViewHolder> {



    private List<ItemExtrato> listaItem;
    public AdapterExtrato(List<ItemExtrato> lista) {
        this.listaItem = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemlista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_extrato, parent, false);
        return new MyViewHolder(itemlista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ItemExtrato itemExtrato = listaItem.get(position);
        holder.letra.setText(itemExtrato.getNome().substring(0,1));
        holder.tipo.setText(itemExtrato.getTipo());
        if (itemExtrato.getValor() > 0){
            holder.quem.setText("de");
            Float valor = itemExtrato.getValor();
            holder.valor.setText("+ R$ " + valor);
        }else if(itemExtrato.getValor() < 0){
            holder.quem.setText("para");
            Float valor = itemExtrato.getValor();
            holder.valor.setText("- R$ " + Math.abs(valor));
            holder.valor.setTextColor(Color.rgb(245,90,90));
        }
        holder.nome.setText(itemExtrato.getNome());
        holder.data.setText(itemExtrato.getData());
    }

    @Override
    public int getItemCount() {
        return listaItem.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView letra;
        TextView tipo;
        TextView quem;
        TextView nome;
        TextView valor;
        TextView data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            letra = itemView.findViewById(R.id.tvLetra);
            tipo = itemView.findViewById(R.id.tvTipo);
            quem = itemView.findViewById(R.id.tvQuem);
            nome = itemView.findViewById(R.id.tvNomeExtrato);
            valor = itemView.findViewById(R.id.tvValor);
            data = itemView.findViewById(R.id.tvData);
        }
    }



}