package com.example.livros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_item", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_item", nullable = false, unique = true)
    private String codigoItem;

    // Opcional: Se quiser navegar do Item para o Aluguel
    @OneToOne(mappedBy = "item")
    private Aluguel aluguel;
}