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
//@JsonTypeName("LIVRO")
public class Filme extends Item {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column
    private String titulo;

    @Column
    private String diretor;
}
