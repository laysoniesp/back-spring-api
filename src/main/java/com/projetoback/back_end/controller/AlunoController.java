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

    private final ConcurrentMap<Long, Aluno> alunos = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @GetMapping
    public List<Aluno> listar() {
        return new ArrayList<>(alunos.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        Aluno aluno = alunos.get(id);
        return aluno == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        aluno.setId(proximoId.getAndIncrement());
        alunos.put(aluno.getId(), aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        if (!alunos.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        aluno.setId(id);
        alunos.put(id, aluno);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return alunos.remove(id) == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.noContent().build();
    }
}
