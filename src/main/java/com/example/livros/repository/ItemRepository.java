package com.example.livros.repository;

import com.example.livros.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT id, codigo_item, tipo_item, titulo, qtd_exemplares_disponiveis, ano_publicacao FROM item", nativeQuery = true)
    List<Item> findAll();
}
