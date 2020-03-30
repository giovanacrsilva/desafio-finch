package br.com.lanchonete.service;

import br.com.lanchonete.model.Lanche;

import java.util.List;
import java.util.Optional;

public interface LancheService {

    List<Lanche> listarTodos();

    Optional<Lanche> listarPorId(long id);

    Optional<Lanche> listarPorDescricao(String descricao);

    void salvar(Lanche lanche);

    void remover(long id);

    void removerTodos();
}
