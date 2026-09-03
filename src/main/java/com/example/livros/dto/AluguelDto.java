package com.example.livros.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDto {
    private Long idAluno;
    private Long idItem;
    private String tipoItem;
}
