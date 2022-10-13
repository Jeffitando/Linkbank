package com.example.linkbank.model;

public class ItemNotifier {
    private String mensagem;

    public ItemNotifier(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
