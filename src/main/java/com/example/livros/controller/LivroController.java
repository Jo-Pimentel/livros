package com.example.livros.controller;

import com.example.livros.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.livros.service.LivroService;
import java.util.List;

@RestController
@RequestMapping("/api-livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Livro> buscarLivros() {
        return livroService.buscarLivros();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Livro buscarLivroPorId(@PathVariable Long id) {
        return livroService.buscarLivroPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro salvarLivro(@RequestBody Livro livro) {
        return livroService.salvarLivro(livro);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.atualizarLivro(id, livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
    }
}
