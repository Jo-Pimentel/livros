package com.example.livros.controller;

import com.example.livros.exception.DadosAusentesException;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.model.Aluno;
import com.example.livros.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/buscarAlunos")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> buscarAlunos() {
        return alunoService.buscarAlunos();
    }

    @GetMapping("/buscarAlunoPorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno buscarAlunoPorId(@PathVariable Long id) throws EntidadeNaoEncontradaException {
        return alunoService.buscarAlunoPorId(id);
    }

    @PostMapping("/salvarAluno")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvarAluno(@RequestBody Aluno aluno) throws DadosAusentesException {
        return alunoService.salvarAluno(aluno);
    }

    @PutMapping("/atualizarAluno/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) throws EntidadeNaoEncontradaException, DadosAusentesException {
        return alunoService.atualizarAluno(id, aluno);
    }

    @DeleteMapping("/deletarAluno/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Long id) throws EntidadeNaoEncontradaException {
        alunoService.deletarAuno(id);
    }
}
