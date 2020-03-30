package br.com.lanchonete.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(schema = "public", name = "lanche")
public class Lanche {

    @Id
    @GeneratedValue
    private long lancheId;

    @NotNull
    @Size(max = 100)
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @OneToMany
    @JoinColumn(name = "cardapio_id")
    private List<Cardapio> cardapios;
}