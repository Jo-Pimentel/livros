package com.example.livros.service;

import com.example.livros.dto.AluguelDto;
import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.*;
import com.example.livros.repository.AluguelRepository;
import com.example.livros.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.livros.repository.LivroRepository;
import com.example.livros.repository.FilmeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        //Item item;
        //Optional<Item> optionalItem;

        Aluno aluno = alunoRepository.findById(aluguelDto.getIdAluno())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com o ID " + aluguelDto.getIdItem() + " não encontrado."));

        aluguel.setAluno(aluno);

        if(aluguelDto.getTipoItem().equalsIgnoreCase("LIVRO")) {
            Livro item = livroRepository.findById(aluguelDto.getIdItem())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + aluguelDto.getIdItem() + " não encontrado."));
            //item.getAlunosLocatarios().add(aluno);
            aluguel.getAluno().setItem(item);
            aluguel.setItem(item);
            livroRepository.save(item);
        } else {
            Filme item = filmeRepository.findById(aluguelDto.getIdItem())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + aluguelDto.getIdItem() + " não encontrado."));
            //item.getAlunosLocatarios().add(aluno);
            aluguel.getAluno().setItem(item);
            aluguel.setItem(item);
            filmeRepository.save(item);
        }

        LocalDate dataAluguel = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.now().plusWeeks(1);
        Boolean devolvido = false;

        aluguel.setDataAluguel(dataAluguel);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setDevolvido(devolvido);

        return aluguelRepository.save(aluguel);
    }

    public String devolucao(Long id) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        Aluguel aluguel = aluguelRepository.getById(id);

        if(aluguel.getDevolvido()) {
            throw new ItemIndisponivelException(aluguel.getItem().getTitulo() + " não está alugado no momento.");
        }

        if(aluguel.getItem().getTipoItem().equalsIgnoreCase("LIVRO")) {
            Livro livro = livroRepository.findById(aluguel.getItem().getId())
                    .orElseThrow();
            livroRepository.save(livro);
        } else {
            Filme filme = filmeRepository.findById(aluguel.getItem().getId())
                    .orElseThrow();
            filmeRepository.save(filme);
        }

        aluguel.setDevolvido(true);
        aluguel.setDevolvidoEm(LocalDate.now());
        aluguel.getAluno().setItem(null);
        alunoRepository.save(aluguel.getAluno());
        aluguelRepository.save(aluguel);
        return aluguel.getItem().getTitulo() + " devolvido com sucesso.";
    }

    public String prorrogarDevolucao(Long id) throws ItemIndisponivelException {
        Aluguel aluguel = aluguelRepository.getById(id);

        if(aluguel.getDevolvido()) {
            throw new ItemIndisponivelException(aluguel.getItem().getTitulo() + " não está alugado no momento.");
        }

        aluguel.setDataDevolucao(aluguel.getDataDevolucao().plusWeeks(1));
        aluguelRepository.save(aluguel);
        return "A devolução de " + aluguel.getItem().getTitulo() + " foi prorrogada para " + aluguel.getDataDevolucao();
    }
}
