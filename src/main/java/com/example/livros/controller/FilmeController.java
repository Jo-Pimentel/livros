package com.example.livros.controller;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.ItemNaoEncontradoException;
import com.example.livros.model.Filme;
import com.example.livros.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.livros.service.LivroService;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/filmes")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Filme> buscarFilmes() {
        return filmeService.buscarFilmes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Filme buscarFilmePorId(@PathVariable Long id) throws ItemNaoEncontradoException {
        return filmeService.buscarFilmePorId(id);
    }
}
