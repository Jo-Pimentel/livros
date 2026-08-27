package com.example.livros.service;

import com.example.livros.repository.LivroRepository;
import com.example.livros.model.Livro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> buscarLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());

        return livroRepository.save(livro);
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }
}
