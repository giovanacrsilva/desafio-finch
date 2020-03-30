package br.com.lanchonete.service;

import br.com.lanchonete.model.Ingrediente;

import java.util.List;
import java.util.Optional;

public interface IngredienteService {

    List<Ingrediente> listarTodos();

    Optional<Ingrediente> listarPorId(long id);

    Optional<Ingrediente> listarPorDescricao(String descricao);

    void salvar(Ingrediente ingrediente);

    void salvarTodos(Ingrediente... ingredientes);

    void atualizar(long id, String novoPreco);

    void removerTodos();
}