package com.example.linkbank;

public class Pessoa {

    static float saldo = 3500f;
    static float saldodigitado;
    static String nome;
    static String cpf, email, chavealeatoria;
    static String nomedigitado, cpfdigitado, emaildigitado, chavealeatoriadigitado;
    static int telefone;
    static int telefonedigitado;

    public static String getNome() {

        return nome;
    }

    public static void setNome(String nome) {

        Pessoa.nome = nome;
    }

    public static float getSaldodigitado() {

        return saldodigitado;
    }

    public static void setSaldodigitado(float saldodigitado) {

        Pessoa.saldodigitado = saldodigitado;
    }

    public static float getSaldo() {

        return saldo;
    }
    public static void setSaldo(float saldo) {

        Pessoa.saldo = saldo;
    }

    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
        Pessoa.cpf = cpf;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Pessoa.email = email;
    }

    public static String getNomedigitado() {
        return nomedigitado;
    }

    public static void setNomedigitado(String nomedigitado) {
        Pessoa.nomedigitado = nomedigitado;
    }

    public static String getCpfdigitado() {
        return cpfdigitado;
    }

    public static void setCpfdigitado(String cpfdigitado) {
        Pessoa.cpfdigitado = cpfdigitado;
    }

    public static String getEmaildigitado() {
        return emaildigitado;
    }

    public static void setEmaildigitado(String emaildigitado) {
        Pessoa.emaildigitado = emaildigitado;
    }

    public static int getTelefone() {
        return telefone;
    }

    public static void setTelefone(int telefone) {
        Pessoa.telefone = telefone;
    }

    public static int getTelefonedigitado() {
        return telefonedigitado;
    }

    public static void setTelefonedigitado(int telefonedigitado) {
        Pessoa.telefonedigitado = telefonedigitado;
    }

    public static String getChavealeatoria() {
        return chavealeatoria;
    }

    public static void setChavealeatoria(String chavealeatoria) {
        Pessoa.chavealeatoria = chavealeatoria;
    }

    public static String getChavealeatoriadigitado() {
        return chavealeatoriadigitado;
    }

    public static void setChavealeatoriadigitado(String chavealeatoriadigitado) {
        Pessoa.chavealeatoriadigitado = chavealeatoriadigitado;
    }
}
