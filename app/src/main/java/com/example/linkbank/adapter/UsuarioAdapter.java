package com.example.linkbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkbank.R;
import com.example.linkbank.model.Usuario;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsuarioAdapter  extends RecyclerView.Adapter<UsuarioAdapter.MyViewHolder> {

    private final List<Usuario> usuarioList;
    private final OnClick onClick;

    public UsuarioAdapter(List<Usuario> usuarioList, OnClick onClick) {
        this.usuarioList = usuarioList;
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_usuario, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.MyViewHolder holder, int position) {

        Usuario usuario = usuarioList.get(position);
        holder.textUsuario.setText(usuario.getNome());

        if (usuario.getUrlImagem().equals("")) {
            return;
        }else{
            Picasso.get().load(usuario.getUrlImagem())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.user)
                    .into(holder.imagemUsuario);
        }

        holder.itemView.setOnClickListener(v -> onClick.OnClickListener(usuario));

    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public interface OnClick {
        void OnClickListener(Usuario usuario);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imagemUsuario;
        TextView textUsuario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            imagemUsuario = itemView.findViewById(R.id.imagemUsuario);
            textUsuario = itemView.findViewById(R.id.textUsuario);
        }
    }

}
