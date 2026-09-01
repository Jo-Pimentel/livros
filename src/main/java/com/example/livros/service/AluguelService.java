package com.example.livros.service;

import com.example.livros.exception.EntidadeNaoEncontradaException;
import com.example.livros.exception.ItemIndisponivelException;
import com.example.livros.model.Aluguel;
import com.example.livros.model.Filme;
import com.example.livros.model.Item;
import com.example.livros.repository.AluguelRepository;
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

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.findAll();
    }

    public Aluguel realizarAluguel(Aluguel aluguel) throws ItemIndisponivelException, EntidadeNaoEncontradaException {
        /*Item item;

        if(tipoItem.equalsIgnoreCase("livro")) {
            item = livroRepository.findById(itemId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro com o ID " + itemId + " não encontrado."));
        } else {
            item = filmeRepository.findById(itemId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme com o ID " + itemId + " não encontrado."));
        }*/
        LocalDate dataAluguel = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.now().plusWeeks(1);
        Boolean devolvido = false;

        aluguel.setDataAluguel(dataAluguel);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setDevolvido(devolvido);

        return aluguelRepository.save(aluguel);
    }
}
