package com.example.livros.repository;

import com.example.livros.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    /*@Query(value = "SELECT tipo_item FROM item WHERE id = ?")
    String buscarTipoItem(Long id);*/
}
