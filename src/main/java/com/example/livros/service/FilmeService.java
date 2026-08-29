package com.example.livros.service;

import com.example.livros.exception.DadosAusentesException;
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

    public boolean findMissingData(Filme filme) {
        return filme.getTitulo() == null || filme.getDiretor() == null;
    }

    public List<Filme> buscarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme buscarFilmePorId(Long id) throws ItemNaoEncontradoException {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException("Filme com o ID " + id + " não encontrado."));
    }

    public Filme salvarFilme(Filme filme) throws DadosAusentesException {
        if(findMissingData(filme)) {
            throw new DadosAusentesException("Opa, algum campo não foi informado!");
        }
        return filmeRepository.save(filme);
    }

    public Filme atualizarFilme(Long id, Filme filmeAtualizado) throws ItemNaoEncontradoException, DadosAusentesException {
        if(!filmeRepository.existsById(id)) {
            throw new ItemNaoEncontradoException("Filme com o ID " + id + " não encontrado");
        }

        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException("Filme com o ID " + id + " não encontrado."));

        if(findMissingData(filmeAtualizado)) {
            throw new DadosAusentesException("Opa, algum campo não foi informado.");
        }

        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setDiretor(filmeAtualizado.getDiretor());

        return filmeRepository.save(filme);
    }

    public void deletarFilme(Long id) throws ItemNaoEncontradoException {
        if(!filmeRepository.existsById(id)) {
            throw new ItemNaoEncontradoException("Filme com o ID " + id + " não encontrado.");
        }
        filmeRepository.deleteById(id);
    }
}
