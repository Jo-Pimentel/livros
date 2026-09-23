package com.example.livros.repository;

import com.example.livros.model.Aluno;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(value = "SELECT id FROM aluno WHERE cpf = ?", nativeQuery = true)
    Long findIdByCpf(String cpf);

    /*@Query(value = "SELECT * FROM aluno ORDER BY id LIMIT 2 OFFSET 10")
    Page<Aluno> buscarAlunosNaPagina(Pageable pageable);*/

    /*@NonNull
    Page<Aluno> findAllInPage(@NonNull Pageable pageable);*/
}
