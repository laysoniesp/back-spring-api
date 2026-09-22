package com.aluno.service;

import com.aluno.entity.Aluno;

public class AlunoService {

    public Aluno criarAluno(String nome, double nota) {
        Aluno alunoEntity = new Aluno(nome, nota);
        return alunoEntity;
    }

    

}
