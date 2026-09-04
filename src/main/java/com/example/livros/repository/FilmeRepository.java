package com.example.livros.repository;

import com.example.livros.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.livros.model.Filme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    @Query(value = "SELECT * FROM filme WHERE codigo_item = ?", nativeQuery = true)
    Filme findByCodigoFilme(String codigoFilme);
}
