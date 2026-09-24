package com.example.livros.repository;

import com.example.livros.model.Aluguel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    @Query(value = "SELECT id_item FROM aluguel_item WHERE id_aluguel=?", nativeQuery = true)
    List<Long> buscarIdsItensAlugados(Long id);

    //Page<Aluguel> listarAlugueisPorPagina(Pageable pageable);
}
