package com.example.livros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID próprio da tabela de aluguel

    // Aponta para a Entidade Aluno usando o campo 'cpf' (que tem unique=true em Aluno)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf_aluno", referencedColumnName = "cpf_aluno")
    private Aluno aluno;

    // Aponta para a Entidade Item usando o campo 'codigoItem' (que tem unique=true em Item)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_item", referencedColumnName = "codigo_item")
    private Item item;

    @Column(name = "tipo_item")
    private String tipoItem;

    @Column(name = "data_aluguel")
    private LocalDate dataAluguel;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "devolvido")
    private boolean devolvido;

    @Column(name = "devolvido_em")
    private LocalDate devolvidoEm;
}