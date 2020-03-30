package br.com.lanchonete.service;

import br.com.lanchonete.model.Cardapio;
import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.model.Lanche;

import java.util.List;
import java.util.Optional;

public interface CardapioService {

    List<Cardapio> listarTodos();

    Optional<Cardapio> listarPorId(long id);

    void salvar(Cardapio cardapio);

    void montar(Lanche lanche, Ingrediente... ingredientes);

    void remover(long id);

    void removerTodos();
}
