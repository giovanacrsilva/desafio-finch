package br.com.lanchonete.model;

import br.com.lanchonete.utils.StringUtils;
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
@Table(schema = "public", name = "ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue
    private long ingredienteId;

    @NotNull
    @Size(max = 100)
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id")
    private List<Cardapio> cardapios;

    public Ingrediente(String descricao, BigDecimal preco) {
        this.descricao = StringUtils.unnaccent(descricao.toLowerCase());
        this.preco = preco;
    }
}