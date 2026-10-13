package com.aluno.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluno.entity.Aluno;
import com.aluno.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody CriarAlunoRequest request) {
        if (request.nota() == null) {
            throw new IllegalArgumentException("A nota do aluno é obrigatória.");
        }

        Aluno aluno = alunoService.criarAluno(request.nome(), request.nota());
        return ResponseEntity
                .created(URI.create("/api/alunos/" + aluno.getId()))
                .body(aluno);
    }

    @GetMapping
    public ResponseEntity<java.util.List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarEntradaInvalida(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("erro", exception.getMessage()));
    }

    public record CriarAlunoRequest(String nome, Double nota) {
    }
}
