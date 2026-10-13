package com.Aluno.Aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.aluno.entity.Aluno;
import com.aluno.repository.AlunoRepository;
import com.aluno.service.AlunoService;

class AlunoApplicationTests {

	@Test
	void criaAlunoComNomeNormalizadoENotaValida() {
		AlunoRepository repository = mock(AlunoRepository.class);
		when(repository.salvarAluno(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));
		AlunoService service = new AlunoService(repository);

		Aluno aluno = service.criarAluno("  Layson  ", 8.6);

		assertEquals("Layson", aluno.getNome());
		assertEquals(8.6, aluno.getNota());
		verify(repository).salvarAluno(aluno);
	}

	@Test
	void rejeitaNomeNuloVazioOuAcimaDoLimite() {
		AlunoRepository repository = mock(AlunoRepository.class);
		AlunoService service = new AlunoService(repository);

		assertThrows(IllegalArgumentException.class, () -> service.criarAluno(null, 8.6));
		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("  ", 8.6));
		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("a".repeat(101), 8.6));
		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("Lay\nson", 8.6));
		verifyNoInteractions(repository);
	}

	@Test
	void rejeitaNotasForaDoIntervaloOuNaoFinitas() {
		AlunoRepository repository = mock(AlunoRepository.class);
		AlunoService service = new AlunoService(repository);

		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("Layson", -0.1));
		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("Layson", 10.1));
		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("Layson", Double.NaN));
		assertThrows(IllegalArgumentException.class, () -> service.criarAluno("Layson", Double.POSITIVE_INFINITY));
		verifyNoInteractions(repository);
	}
}
