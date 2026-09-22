package com.aluno.service;

import org.springframework.stereotype.Service;

import com.aluno.repository.AlunoRepository;
import com.aluno.entity.Aluno;

@Service 
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno criarAluno(String nome, double nota) {
        Aluno alunoEntity = new Aluno(nome, nota);
        alunoRepository.salvarAluno(alunoEntity);
        return alunoEntity;
    }



}
