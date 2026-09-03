package com.example.livros.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
//@JsonTypeName("LIVRO")
public class Livro extends Item {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;
}
