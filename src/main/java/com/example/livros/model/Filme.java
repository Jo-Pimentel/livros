package com.example.livros.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("filme")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filme extends Item {
    @Column(name = "diretor")
    private String diretor;

    @Column(name = "duracao_em_minutos")
    private Integer duracaoEmMinutos;
}
