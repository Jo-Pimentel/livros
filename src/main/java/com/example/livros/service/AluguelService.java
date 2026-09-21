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

    //private List<Item> listaAux;

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.findAll();
    }

    public Aluguel buscarAluguelEspecifico(Long id) throws EntidadeNaoEncontradaException {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluguel com o ID " + id + " não encontrado."));
    }

    public Aluguel realizarAluguel(AluguelDto aluguelDto) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        Aluguel aluguel = new Aluguel();

        if(alunoRepository.findByCpf(aluguelDto.getCpfAluno()) == null) {
            throw new EntidadeNaoEncontradaException("Aluno com o CPF " + aluguelDto.getCpfAluno() + " não encontrado.");
        }

        Aluno aluno = alunoRepository.findByCpf(aluguelDto.getCpfAluno());

        aluguel.setAluno(aluno);

        for(int i = 0; i < aluguelDto.getIdsItens().size(); i++) {
            if(aluguelDto.getTiposItens().get(i).equalsIgnoreCase("LIVRO")) {
                Livro livro = livroRepository.findById(aluguelDto.getIdsItens().get(i))
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro não encontrado."));
                livro.setQtdExemplaresDisponiveis(livro.getQtdExemplaresDisponiveis() - 1);
                aluguel.getItens().add(livro);
            } else {
                Filme filme = filmeRepository.findById(aluguelDto.getIdsItens().get(i))
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado."));
                filme.setQtdExemplaresDisponiveis(filme.getQtdExemplaresDisponiveis() - 1);
                aluguel.getItens().add(filme);
            }
        }
        /*if(aluguelDto.getTipoItem().equalsIgnoreCase("LIVRO")) {
            Livro livro = livroRepository.findById(aluguelDto.getIdItem())
                            .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + aluguelDto.getIdItem() + " não encontrado."));
            aluguel.getAluno().setItem(livro);
            aluguel.setItem(livro);
            livroRepository.save(livro);
        } else {
            Filme filme = filmeRepository.findById(aluguelDto.getIdItem())
                            .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme com o ID " + aluguelDto.getIdItem() + " não encontrado."));
            aluguel.getAluno().setItem(filme);
            aluguel.setItem(filme);
            filmeRepository.save(filme);
        }*/

        LocalDate dataAluguel = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.now().plusMonths(aluguelDto.getQtdMesesAluguel());

        //aluguel.getItem().setQtdExemplaresDisponiveis(aluguel.getItem().getQtdExemplaresDisponiveis() - 1);
        aluguel.setDataAluguel(dataAluguel);
        aluguel.setDataDevolucao(dataDevolucao);

        return aluguelRepository.save(aluguel);
    }

    /*public String devolucao(Long id) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        Aluguel aluguel = aluguelRepository.getById(id);

        if(aluguel.getDevolvido()) {
            throw new ItemIndisponivelException(aluguel.getItem().getTitulo() + " não está alugado no momento.");
        }

        if(aluguel.getItens().getTipoItem().equalsIgnoreCase("LIVRO")) {
            Livro livro = livroRepository.findById(aluguel.getItem().getId())
                    .orElseThrow();
            livroRepository.save(livro);
        } else {
            Filme filme = filmeRepository.findById(aluguel.getItem().getId())
                    .orElseThrow();
            filmeRepository.save(filme);
        }

        aluguel.getItem().setQtdExemplaresDisponiveis(aluguel.getItem().getQtdExemplaresDisponiveis() + 1);
        aluguel.setDevolvido(true);
        aluguel.setDevolvidoEm(LocalDate.now());
        aluguel.getAluno().setAlugando(false);
        aluguel.getAluno().setItem(null);
        alunoRepository.save(aluguel.getAluno());
        aluguelRepository.save(aluguel);
        return aluguel.getItem().getTitulo() + " devolvido com sucesso.";
    }*/

    /*public String prorrogarDevolucao(Long id) throws ItemIndisponivelException {
        Aluguel aluguel = aluguelRepository.getById(id);

        if(aluguel.getDevolvido()) {
            throw new ItemIndisponivelException(aluguel.getItem().getTitulo() + " não está alugado no momento.");
        }

        aluguel.setDataDevolucao(aluguel.getDataDevolucao().plusWeeks(1));
        aluguelRepository.save(aluguel);
        return "A devolução de " + aluguel.getItem().getTitulo() + " foi prorrogada para " + aluguel.getDataDevolucao();
    }*/
}
