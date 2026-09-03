package com.example.livros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("livro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro extends Item {
    @Column(name = "autor")
    private String autor;

    @Column(name = "editora")
    private String editora;

    @Column(name = "qtd_paginas")
    private Integer qtdPaginas;
}
