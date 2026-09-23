package com.example.livros.service;

import com.example.livros.exception.CpfInvalidoException;
import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.Aluno;
import com.example.livros.repository.AlunoRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

    /*public boolean validarCpf(Long cpf) {
        List<Integer> listaNumeros = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2);
        List<Long> digitosCpf = List.of(cpf);
        Long resultadoSoma = 0L;

        for(int i = 0; i < listaNumeros.size(); i++) {
            resultadoSoma += listaNumeros.get(i) * digitosCpf.get(i);
        }
    }*/

    public List<Aluno> buscarAlunosNaPagina(int qtdItensPorPagina, int paginaAtual) {
        List<Aluno> todosOsAlunos = this.buscarAlunos();

        int indexInicial = qtdItensPorPagina * paginaAtual;

        return todosOsAlunos.subList(indexInicial, indexInicial + qtdItensPorPagina - paginaAtual);
    }

    public List<Aluno> buscarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) throws EntidadeNaoEncontradaException {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + id + " não encontrado."));
    }

    public Aluno buscarAlunoPorCpf(String cpf) throws EntidadeNaoEncontradaException {
        if(alunoRepository.findIdByCpf(cpf) == null) {
            throw new EntidadeNaoEncontradaException("Aluno com o CPF " + cpf + " não encontrado.");
        } else {
            Long idAluno = alunoRepository.findIdByCpf(cpf);
            return alunoRepository.findById(idAluno)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + idAluno + " não encontrado."));
        }
    }

    public Page<Aluno> buscarAlunosPorPagina(Pageable pageable) {
        return alunoRepository.findAll(pageable);
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

    public Aluno atualizarAlunoPorCpf(String cpf, Aluno alunoAtualizado) throws EntidadeNaoEncontradaException, DadosAusentesException {
        Aluno aluno = this.buscarAlunoPorCpf(cpf);

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setCpf(alunoAtualizado.getCpf());

        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) throws EntidadeNaoEncontradaException {
        if(!alunoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Auno com o ID " + id + " não encontrado.");
        }
        alunoRepository.deleteById(id);
    }
}
