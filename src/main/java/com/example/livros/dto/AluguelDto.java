package com.example.livros.dto;

import com.example.livros.model.Filme;
import com.example.livros.model.Item;
import com.example.livros.model.Livro;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDto {
    private String cpfAluno;
    private List<Long> idsItens;
    private List<String> tiposItens;
    private Integer qtdMesesAluguel;
    //private String tipoItem;
}
