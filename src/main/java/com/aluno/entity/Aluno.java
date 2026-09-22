package com.aluno.entity;


public class Aluno {
    private long id;
    private String nome;
    private double nota;

    public Aluno() {
    }
    
    public Aluno(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public Aluno(long id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public boolean estaAprovado() {
        return this.nota >= 7.0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    
}
    