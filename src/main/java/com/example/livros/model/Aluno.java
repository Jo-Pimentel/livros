package com.example.livros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf_aluno", nullable = false, unique = true)
    private String cpf;

    // Opcional: Se quiser navegar do Aluno para o Aluguel
    @OneToOne(mappedBy = "aluno")
    private Aluguel aluguel;
}