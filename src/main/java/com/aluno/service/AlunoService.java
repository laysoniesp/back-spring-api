package com.aluno.service;

import org.springframework.stereotype.Service;

import com.aluno.entity.Aluno;

@Service 
public class AlunoService {

    
    public Aluno criarAluno(String nome, double nota) {
        Aluno alunoEntity = new Aluno(nome, nota);
        return alunoEntity;
    }



}
