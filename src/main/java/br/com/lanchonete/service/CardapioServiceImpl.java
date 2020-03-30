package br.com.lanchonete.service;

import br.com.lanchonete.controller.IngredienteController;
import br.com.lanchonete.model.Cardapio;
import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.model.Lanche;
import br.com.lanchonete.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<Optional<Cardapio>> listarPorLancheDescricao(String descricao) {
        return cardapioRepository.findByLancheDescricao(descricao);
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
        List<Optional<Cardapio>> cardapios = listarPorLancheDescricao(lanche.getDescricao());

		List<Ingrediente> ingredientes = new ArrayList<>();
		for (Optional<Cardapio> optionalCardapio : cardapios) {
			Cardapio cardapio = optionalCardapio.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o Cardápio do Lanche: " + lanche.getDescricao()));
			ingredientes.add(cardapio.getIngrediente());
		}

        //light
        if (ingredientes.stream().anyMatch(ingrediente -> ingrediente.getDescricao().equals("alface"))
                && ingredientes.stream().noneMatch(ingrediente -> ingrediente.getDescricao().equals("bacon"))) {
            lanche.setPreco(lanche.getPreco().multiply(new BigDecimal("0.9")));
        }

        //muita carne
        if (ingredientes.stream().filter(ingrediente -> ingrediente.getDescricao().equals("hamburguer")).count() >= 3) {
            long qtdeADescontar = ingredientes.stream().filter(ingrediente -> ingrediente.getDescricao().equals("hamburguer")).count() / 3;
            lanche.setPreco(lanche.getPreco().subtract(ingredienteController.listarPorDescricao("hamburguer").getPreco().multiply(new BigDecimal(qtdeADescontar))));
        }

        //muito queijo
        if (ingredientes.stream().filter(ingrediente -> ingrediente.getDescricao().equals("queijo")).count() >= 3) {
            long qtdeADescontar = ingredientes.stream().filter(ingrediente -> ingrediente.getDescricao().equals("queijo")).count() / 3;
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