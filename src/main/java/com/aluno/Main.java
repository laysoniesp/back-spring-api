package com.aluno;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aluno.service.AlunoService;
import com.aluno.entity.Aluno;

@Component 
public class Main implements CommandLineRunner {
     
    private final AlunoService alunoService;

    public Main(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @Override
    public void run(String... args) throws Exception {
        Aluno aluno = alunoService.criarAluno("João", 8.5);
        System.out.println("Aluno criado: " + aluno.getNome() + ", Nota: " + aluno.getNota());
        System.out.println("Aplicação iniciada com sucesso!");
    }
}
