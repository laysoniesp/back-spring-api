package com.aluno;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Scanner;
import com.aluno.service.AlunoService;
import com.aluno.entity.Aluno;

@Component 
public class Main implements CommandLineRunner {
     
    private final AlunoService alunoService;

    public Main(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Substituir pelo controller e endpoints REST
    @Override
    public void run(String... args) throws Exception {
        Aluno aluno = alunoService.criarAluno("João", 8.5);
        System.out.println("Aluno criado: " + aluno.getNome() + ", Nota: " + aluno.getNota());
        System.out.println("Aplicação iniciada com sucesso!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a nota do aluno: ");
        double nota = scanner.nextDouble();
        Aluno novoAluno = alunoService.criarAluno(nome, nota);
        System.out.println("Aluno criado: " + novoAluno.getNome() + ", Nota: " + novoAluno.getNota());

    }
}
