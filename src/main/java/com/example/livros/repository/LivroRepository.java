package com.example.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.livros.model.Livro;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
