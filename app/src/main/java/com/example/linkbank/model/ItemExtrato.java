package com.example.linkbank.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ItemExtrato {

    private String tipo;
    private String nome;
    private float valor;
    private String data;
    private DateTimeFormatter date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ItemExtrato(String tipo, String nome, float valor) {
        this.tipo = tipo;
        this.nome = nome;
        this.valor = valor;
        LocalDateTime now = LocalDateTime.now();
        this.date = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm");
        this.data = this.date.format(now);
    }

    public String getTipo() {
        return tipo;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
