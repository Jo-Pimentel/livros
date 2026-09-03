package com.example.livros.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "tipo_item", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoItem" // Deve bater com a chave usada dentro do objeto "item" no JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Livro.class, name = "LIVRO"),
        @JsonSubTypes.Type(value = Filme.class, name = "FILME")
})*/
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "codigo_item", nullable = false, unique = true)
    private String codigoItem;

    @OneToOne(mappedBy = "item")
    private Aluguel aluguel;

    @Column(name = "tipo_item")
    private String tipoItem;
}