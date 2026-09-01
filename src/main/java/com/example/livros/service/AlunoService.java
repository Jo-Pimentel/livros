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

    public Aluno atualizarAluno(Long id, Aluno alunoAtualizado) throws EntidadeNaoEncontradaException, DadosAusentesException {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + id + " não encontrado."));

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setCpf(alunoAtualizado.getCpf());

        return alunoRepository.save(aluno);
    }

    public void deletarAuno(Long id) throws EntidadeNaoEncontradaException {
        if(!alunoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Auno com o ID " + id + " não encontrado.");
        }
        alunoRepository.deleteById(id);
    }
}
