package com.example.livros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne
    @JoinColumn(name = "id_aluno", referencedColumnName = "id")
    private Aluno aluno;

    // Aponta para a Entidade Item usando o campo 'codigoItem' (que tem unique=true em Item)
    @ManyToOne
    @JoinColumn(name = "id_item", referencedColumnName = "id")
    private Item item;

    @Column(name = "data_aluguel")
    private LocalDate dataAluguel;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "devolvido")
    private Boolean devolvido = false;

    @Column(name = "devolvido_em")
    private LocalDate devolvidoEm;
}