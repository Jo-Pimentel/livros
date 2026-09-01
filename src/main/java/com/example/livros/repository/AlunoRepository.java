package com.example.livros.repository;

import com.example.livros.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(value = "SELECT * FROM aluno WHERE cpf = ?", nativeQuery = true)
    Aluno findByCpf(String cpf);
}
