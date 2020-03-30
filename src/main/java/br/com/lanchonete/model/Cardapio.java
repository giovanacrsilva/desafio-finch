package br.com.lanchonete.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "cardapio")
public class Cardapio {

    @Id
    @GeneratedValue
    @Column(name = "cardapio_id")
    private long cardapioId;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lanche_id")
    private Lanche lanche;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Transient
    private List<Ingrediente> ingredientes;

    public Cardapio(Lanche lanche, Ingrediente ingrediente) {
        this.lanche = lanche;
        this.ingrediente = ingrediente;
    }
}