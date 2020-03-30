package br.com.lanchonete.service;

import br.com.lanchonete.controller.IngredienteController;
import br.com.lanchonete.model.Cardapio;
import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.model.Lanche;
import br.com.lanchonete.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CardapioServiceImpl implements CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private LancheServiceImpl lancheService;

    @Autowired
    private IngredienteController ingredienteController;

    @Override
    public List<Cardapio> listarTodos() {
        return cardapioRepository.findAll();
    }

    @Override
    public Optional<Cardapio> listarPorId(long id) {
        return cardapioRepository.findById(id);
    }

    @Override
    public void salvar(Cardapio cardapio) {
        cardapioRepository.save(cardapio);
    }

    @Override
    public void montar(Lanche lanche, Ingrediente... ingredientes) {
        lancheService.salvar(lanche);

        for (Ingrediente ingrediente : ingredientes) {
            lanche.setPreco(lanche.getPreco().add(ingrediente.getPreco()));
            salvar(new Cardapio(lanche, ingrediente));
        }

        aplicarPromocoes(lanche);
        lancheService.salvar(lanche);
    }

    private void aplicarPromocoes(Lanche lanche) {
        //light
        if (lanche.getCardapios().stream().anyMatch(cardapio -> cardapio.getIngrediente().getDescricao().contains("alface"))
                && lanche.getCardapios().stream().noneMatch(cardapio -> cardapio.getIngrediente().getDescricao().contains("bacon"))) {
            lanche.setPreco(lanche.getPreco().multiply(new BigDecimal("0.9")));
        }

        //muita carne
        if (lanche.getCardapios().stream().filter(cardapio -> cardapio.getIngrediente().getDescricao().contains("hamburguer")).count() >= 3) {
            long qtdeADescontar = lanche.getCardapios().stream().filter(cardapio -> cardapio.getIngrediente().getDescricao().contains("hamburguer")).count() / 3;
            lanche.setPreco(lanche.getPreco().subtract(ingredienteController.listarPorDescricao("hamburguer").getPreco().multiply(new BigDecimal(qtdeADescontar))));
        }

        //muito queijo
        if (lanche.getCardapios().stream().filter(cardapio -> cardapio.getIngrediente().getDescricao().contains("queijo")).count() >= 3) {
            long qtdeADescontar = lanche.getCardapios().stream().filter(cardapio -> cardapio.getIngrediente().getDescricao().contains("queijo")).count() / 3;
            lanche.setPreco(lanche.getPreco().subtract(ingredienteController.listarPorDescricao("queijo").getPreco().multiply(new BigDecimal(qtdeADescontar))));
        }
    }

    @Override
    public void remover(long id) {
        cardapioRepository.deleteById(id);
    }

    @Override
    public void removerTodos() {
        cardapioRepository.deleteAll();
    }
}