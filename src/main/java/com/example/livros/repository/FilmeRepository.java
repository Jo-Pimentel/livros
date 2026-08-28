package com.example.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.livros.model.Filme;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
