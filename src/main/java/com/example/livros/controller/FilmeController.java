package com.example.livros.controller;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.model.Filme;
import com.example.livros.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Filme buscarFilmePorId(@PathVariable Long id) throws EntidadeNaoEncontradaException {
        return filmeService.buscarFilmePorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Filme salvarFilme(@RequestBody Filme filme) throws DadosAusentesException {
        return filmeService.salvarFilme(filme);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) throws EntidadeNaoEncontradaException, DadosAusentesException {
        return filmeService.atualizarFilme(id, filmeAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFilme(@PathVariable Long id) throws EntidadeNaoEncontradaException {
        filmeService.deletarFilme(id);
    }
}
