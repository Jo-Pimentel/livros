package com.example.livros.controller;

import com.example.livros.dto.AluguelDto;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.Aluguel;
import com.example.livros.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/alugueis")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping("/buscarAlugueis")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluguel> listarAlugueis() {
        return aluguelService.listarAlugueis();
    }

    @GetMapping("/buscarAluguelEspecifico/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluguel buscarAluguelEspecifico(@PathVariable Long id) throws EntidadeNaoEncontradaException {
        return aluguelService.buscarAluguelEspecifico(id);
    }

    @PostMapping("/realizarAluguel")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluguel salvarAluguel(@RequestBody AluguelDto aluguelDto) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        //Aluno aluno = alunoService.buscarAlunoPorId(alunoService.buscarAlunoPorCpf());
        return aluguelService.realizarAluguel(aluguelDto);
    }

    @PutMapping("/devolucao/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String devolucao(@PathVariable Long id) {
        return aluguelService.devolucao(id);
    }

    @PutMapping("/prorrogarDevolucao/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String prorrogarDevolucao(@PathVariable Long id) {
        return aluguelService.prorrogarDevolucao(id);
    }
}
