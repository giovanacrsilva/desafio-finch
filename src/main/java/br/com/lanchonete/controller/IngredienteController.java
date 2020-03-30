package br.com.lanchonete.controller;

import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.service.IngredienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteServiceImpl ingredienteService;

    @GetMapping
    public List<Ingrediente> listarTodos() {
        return ingredienteService.listarTodos();
    }

    @GetMapping("/listarPorId/{id}")
    public Ingrediente listarPorId(@PathVariable long id) {
        return ingredienteService.listarPorId(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o Ingrediente com Id: " + id));
    }

    @GetMapping("/listarPorNome/{descricao}")
    public Ingrediente listarPorDescricao(@PathVariable String descricao) {
        return ingredienteService.listarPorDescricao(descricao).orElseThrow(() -> new RuntimeException("Não foi possível encontrar Ingrediente com a Descrição: " + descricao));
    }

    @PostMapping
    public void adicionar(@RequestBody Ingrediente ingrediente) {
        ingredienteService.salvar(ingrediente);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable long id, @RequestBody String novoPreco) {
        ingredienteService.atualizar(id, novoPreco);
    }
}