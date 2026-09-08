package com.projetoback.back_end.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetoback.back_end.entity.Aluno;

@CrossOrigin(origins = "https://hoppscotch.io")
@RestController 
@RequestMapping("/aluno")

public class AlunoController {

    List<Aluno> listaAlunos = new ArrayList<Aluno>();

    @GetMapping
    public List<Aluno> listar() {
        return listaAlunos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        Aluno aluno = listaAlunos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
        return aluno == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(aluno);
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        listaAlunos.add(aluno);
        return aluno;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno alunoExistente = listaAlunos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (alunoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        aluno.setId(id);
        listaAlunos.remove(alunoExistente);
        listaAlunos.add(aluno);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Aluno alunoExistente = listaAlunos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (alunoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        listaAlunos.remove(alunoExistente);
        return ResponseEntity.noContent().build();
    }
}
