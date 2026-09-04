package com.example.livros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"aluguel"})
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf_aluno", nullable = false, unique = true)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties({"aluno", "aluguel"}) // Chave estrangeira para Person
    private Item item;

    // Opcional: Se quiser navegar do Aluno para o Aluguel
    @OneToMany(mappedBy = "aluguel")
    @JsonIgnoreProperties({"aluno", "item"})
    //@Column(name = "aluguel")
    private List<Aluguel> aluguel;
}