package com.projetoback.back_end.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projetoback.back_end.entity.Aluno;

class AlunoControllerTests {

    private final AlunoController controller = new AlunoController();

    @Test
    void deveExecutarCrudDeAluno() {
        ResponseEntity<Aluno> criado = controller.criar(new Aluno(null, "Ana", "123"));

        assertEquals(HttpStatus.CREATED, criado.getStatusCode());
        assertNotNull(criado.getBody());
        assertEquals(1L, criado.getBody().getId());

        ResponseEntity<Aluno> encontrado = controller.buscar(criado.getBody().getId());
        assertEquals(HttpStatus.OK, encontrado.getStatusCode());
        assertEquals("Ana", encontrado.getBody().getNome());

        ResponseEntity<Aluno> atualizado = controller.atualizar(1L, new Aluno(null, "Ana Silva", "123"));
        assertEquals(HttpStatus.OK, atualizado.getStatusCode());
        assertEquals("Ana Silva", atualizado.getBody().getNome());

        ResponseEntity<Void> removido = controller.remover(1L);
        assertEquals(HttpStatus.NO_CONTENT, removido.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, controller.buscar(1L).getStatusCode());
    }

    @Test
    void deveRetornar404ParaAlunoInexistente() {
        assertEquals(HttpStatus.NOT_FOUND, controller.buscar(999L).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND,
                controller.atualizar(999L, new Aluno(null, "Inexistente", "999")).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, controller.remover(999L).getStatusCode());
    }
}
