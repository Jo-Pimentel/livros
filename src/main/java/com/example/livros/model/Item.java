package com.example.livros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"aluguel"})
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Column(name = "codigo_item", nullable = false, unique = true)
    private String codigoItem;

    @OneToMany(mappedBy = "item")
    @JsonIgnoreProperties({"aluno", "item"})
    private List<Aluguel> aluguel;

    @Column(name = "tipo_item")
    private String tipoItem;

    @Column(name = "qtd_exemplares_disponiveis")
    private Integer qtdExemplaresDisponiveis;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"item", "aluguel"})
    //@Column(name = "alunos_locatarios")
    private List<Aluno> alunosLocatarios;
}