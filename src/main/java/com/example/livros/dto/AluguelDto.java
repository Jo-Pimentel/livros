package com.example.livros.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDto {
    private Long idAluno;
    private String codigoItem;
    private String tipoItem;
}
