package com.example.livros.dto;

import com.example.livros.model.Item;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDto {
    private String cpfAluno;
    private List<Item> itens;
    private Integer qtdMesesAluguel;
    //private String tipoItem;
}
