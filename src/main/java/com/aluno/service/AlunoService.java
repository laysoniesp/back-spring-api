package com.aluno.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.aluno.entity.Aluno;
import com.aluno.repository.AlunoRepository;

@Service
public class AlunoService {

    private static final int TAMANHO_MAXIMO_NOME = 100;
    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 10.0;

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno criarAluno(String nome, double nota) {
        String nomeNormalizado = validarNome(nome);
        validarNota(nota);

        Aluno alunoEntity = new Aluno(nomeNormalizado, nota);
        return alunoRepository.salvarAluno(alunoEntity);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.listarAlunos();
    }

    private String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do aluno é obrigatório.");
        }

        String nomeNormalizado = nome.strip();
        if (nomeNormalizado.length() > TAMANHO_MAXIMO_NOME) {
            throw new IllegalArgumentException(
                    "O nome do aluno deve ter no máximo " + TAMANHO_MAXIMO_NOME + " caracteres.");
        }
        if (nomeNormalizado.chars().anyMatch(Character::isISOControl)) {
            throw new IllegalArgumentException("O nome do aluno não pode conter caracteres de controle.");
        }

        return nomeNormalizado;
    }

    private void validarNota(double nota) {
        if (!Double.isFinite(nota) || nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(
                    "A nota deve ser um número entre " + NOTA_MINIMA + " e " + NOTA_MAXIMA + ".");
        }
    }
}
