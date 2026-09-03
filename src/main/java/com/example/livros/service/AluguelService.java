package com.example.livros.service;

import com.example.livros.dto.AluguelDto;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.Aluguel;
import com.example.livros.model.Aluno;
import com.example.livros.model.Filme;
import com.example.livros.model.Item;
import com.example.livros.repository.AluguelRepository;
import com.example.livros.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.livros.repository.LivroRepository;
import com.example.livros.repository.FilmeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AluguelService {
    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.findAll();
    }

    public Aluguel buscarAluguelEspecifico(Long id) throws EntidadeNaoEncontradaException {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluguel com o ID " + id + " não encontrado."));
    }

    public Aluguel realizarAluguel(AluguelDto aluguelDto) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        Aluguel aluguel = new Aluguel();
        Item item;

        if(aluguelDto.getTipoItem().equalsIgnoreCase("LIVRO")) {
            item = livroRepository.findById(aluguelDto.getIdItem())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + aluguelDto.getIdItem() + " não encontrado."));
        } else {
            item = filmeRepository.findById(aluguelDto.getIdItem())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + aluguelDto.getIdItem() + " não encontrado."));
        }

        Aluno aluno = alunoRepository.findById(aluguelDto.getIdItem())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + aluguelDto.getIdItem() + " não encontrado."));

        LocalDate dataAluguel = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.now().plusWeeks(1);
        Boolean devolvido = false;

        aluguel.setAluno(aluno);
        aluguel.setItem(item);
        aluguel.setDataAluguel(dataAluguel);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setDevolvido(devolvido);

        return aluguelRepository.save(aluguel);
    }

    public String devolucao(Long id) {
        Aluguel aluguel = aluguelRepository.getById(id);
        aluguel.setDevolvido(true);
        aluguelRepository.save(aluguel);
        return "Item devolvido com sucesso.";
    }
}
