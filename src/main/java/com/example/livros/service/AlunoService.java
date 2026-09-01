package com.example.livros.service;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.Aluno;
import com.example.livros.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.livros.model.Item;
import com.example.livros.model.Livro;
import com.example.livros.model.Filme;
import com.example.livros.repository.LivroRepository;
import com.example.livros.repository.FilmeRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Aluno> buscarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) throws EntidadeNaoEncontradaException {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + id + " não encontrado."));
    }

    public Aluno salvarAluno(Aluno aluno) throws DadosAusentesException {
        if(aluno.getNome() == null) {
            throw new DadosAusentesException("Opa! Nome do aluno não informado.");
        }
        return alunoRepository.save(aluno);
    }

    public String alugarItem(String tipoItem, Long idItem, Long idAluno) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        Item item;

        if(tipoItem.equalsIgnoreCase("livro")) {
            item = livroRepository.findById(idItem)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + idItem + " não encontrado."));
        } else {
            item = filmeRepository.findById(idItem)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme com o ID " + idItem + " não encontrado."));
        }

        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + idItem + " não encontrado."));

        return "Item alugado com sucesso.";
    }
}
