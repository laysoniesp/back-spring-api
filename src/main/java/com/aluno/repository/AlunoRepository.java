package com.aluno.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aluno.entity.Aluno;

@Repository
public class AlunoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Aluno salvarAluno(Aluno aluno) {
        String sql = """
                INSERT INTO alunos (nome, nota)
                VALUES (?, ?)
                RETURNING id
                """;
        Long id = jdbcTemplate.queryForObject(sql, Long.class, aluno.getNome(), aluno.getNota());
        aluno.setId(id);
        return aluno;
    }

    public java.util.List<Aluno> listarAlunos() {
        String sql = """
                SELECT id, nome, nota
                FROM alunos
                ORDER BY id
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> new Aluno(
                resultSet.getLong("id"),
                resultSet.getString("nome"),
                resultSet.getDouble("nota")));
    }
}
