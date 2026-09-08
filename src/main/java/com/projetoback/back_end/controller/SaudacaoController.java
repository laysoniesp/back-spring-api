package com.projetoback.back_end.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://hoppscotch.io")
public class SaudacaoController {

	@GetMapping("/")
	public String saudar() {
		return "API de alunos funcionando";
	}
}
