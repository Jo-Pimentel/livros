package com.example.livros.controller;

import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.Aluguel;
import com.example.livros.model.Aluno;
import com.example.livros.service.AluguelService;
import com.example.livros.service.AlunoService;
import com.example.livros.service.FilmeService;
import com.example.livros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca/alugueis")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/listagem")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluguel> listarAlugueis() {
        return aluguelService.listarAlugueis();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Aluguel salvarAluguel(@RequestBody Aluguel aluguel) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        Aluno aluno = alunoService.buscarAlunoPorId(aluno.getId());
        return aluguelService.realizarAluguel(aluguel);
    }
}
