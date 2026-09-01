package com.example.livros.service;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.repository.LivroRepository;
import com.example.livros.model.Livro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    private boolean findMissingData(Livro livro) {
        return livro.getTitulo() == null || livro.getAutor() == null;
    }

    public List<Livro> buscarLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id) throws EntidadeNaoEncontradaException {
        return livroRepository.findById(id).
                orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + id + " não econtrado"));
    }

    public Livro salvarLivro(Livro livro) throws DadosAusentesException {
        if(this.findMissingData(livro)) {
            throw new DadosAusentesException("Opa, algum campo não foi informado.");
        }
        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado) throws EntidadeNaoEncontradaException, DadosAusentesException {
        if(!livroRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Livro com o ID " + id + " não encontrado");
        }

        Livro livro = livroRepository.findById(id).
                orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + id + " não econtrado"));

        if(this.findMissingData(livroAtualizado)) {
            throw new DadosAusentesException("Opa, algum campo não foi informado.");
        }

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());

        return livroRepository.save(livro);
    }

    public void deletarLivro(Long id) throws EntidadeNaoEncontradaException {
        if(!livroRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Livro com o ID " + id + " não encontrado");
        }
        livroRepository.deleteById(id);
    }
}
