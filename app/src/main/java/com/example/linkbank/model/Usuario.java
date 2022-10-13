package com.example.linkbank.model;

import com.example.linkbank.adapter.helper.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String urlImagem;
    private double saldo;
    private String senha;
    private String rg;
    private String aniversario;
    private String renda;
    private String limite;
    private String profissao;
    private String cpf;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;
    private String pixCpf;
    private String pixEmail;
    private String pixTelefone;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getPixCpf() {
        return pixCpf;
    }

    public void setPixCpf(String pixCpf) {
        this.pixCpf = pixCpf;
    }

    public String getPixEmail() {
        return pixEmail;
    }

    public void setPixEmail(String pixEmail) {
        this.pixEmail = pixEmail;
    }

    public String getPixTelefone() {
        return pixTelefone;
    }

    public void setPixTelefone(String pixTelefone) {
        this.pixTelefone = pixTelefone;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getRenda() {
        return renda;
    }

    public void setRenda(String renda) {
        this.renda = renda;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Usuario() {
    }

    public void atualizarSaldo(){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuario")
                .child(getId())
                .child("saldo");
        usuarioRef.setValue(getSaldo());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

