package com.projetoback.back_end.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoback.back_end.entity.Aluno;

@RestController 
@RequestMapping("/aluno")

public class AlunoController {
    
    List<Aluno> alunos = new ArrayList<Aluno>();

    public Aluno criar() {

    }

}
