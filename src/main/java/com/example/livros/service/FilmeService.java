package com.example.livros.service;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.EntidadeNaoEncontradaException;
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
        return filme.getTitulo() == null || filme.getDiretor() == null || filme.getCodigoItem() == null;
    }

    public List<Filme> buscarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme buscarFilmePorId(Long id) throws EntidadeNaoEncontradaException {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme com o ID " + id + " não encontrado."));
    }

    public Filme salvarFilme(Filme filme) throws DadosAusentesException {
        if(findMissingData(filme)) {
            throw new DadosAusentesException("Opa, algum campo não foi informado!");
        }
        filme.setTipoItem("FILME");
        return filmeRepository.save(filme);
    }

    public Filme atualizarFilme(Long id, Filme filmeAtualizado) throws EntidadeNaoEncontradaException, DadosAusentesException {
        if(!filmeRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Filme com o ID " + id + " não encontrado");
        }

        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme com o ID " + id + " não encontrado."));

        if(findMissingData(filmeAtualizado)) {
            throw new DadosAusentesException("Opa, algum campo não foi informado.");
        }

        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setDiretor(filmeAtualizado.getDiretor());

        return filmeRepository.save(filme);
    }

    public void deletarFilme(Long id) throws EntidadeNaoEncontradaException {
        if(!filmeRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Filme com o ID " + id + " não encontrado.");
        }
        filmeRepository.deleteById(id);
    }
}
