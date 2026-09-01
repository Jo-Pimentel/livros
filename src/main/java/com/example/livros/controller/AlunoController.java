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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> buscarAlunos() {
        return alunoService.buscarAlunos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno buscarAlunoPorId(@PathVariable Long id) throws EntidadeNaoEncontradaException {
        return alunoService.buscarAlunoPorId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvarAluno(@RequestBody Aluno aluno) throws DadosAusentesException {
        return alunoService.salvarAluno(aluno);
    }
}
