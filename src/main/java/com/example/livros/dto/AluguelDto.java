package com.example.livros.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDto {
    private String cpfAluno;
    private Long idItem;
    private String tipoItem;
}
