package com.example.livros.service;

import com.example.livros.exception.ItemNaoEncontradoException;
import com.example.livros.model.Filme;
import com.example.livros.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> buscarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme buscarFilmePorId(Long id) throws ItemNaoEncontradoException {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException("Filme com o ID " + id + " não encontrado."));
    }
}
