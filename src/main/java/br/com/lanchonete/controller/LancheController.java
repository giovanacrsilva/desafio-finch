package br.com.lanchonete.controller;

import br.com.lanchonete.model.Lanche;
import br.com.lanchonete.service.LancheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lanches")
public class LancheController {

    @Autowired
    private LancheServiceImpl lancheService;

    @GetMapping
    public List<Lanche> listarTodos() {
        return lancheService.listarTodos();
    }

    @GetMapping("/listarPorId/{id}")
    public Lanche listarPorId(@PathVariable long id) {
        return lancheService.listarPorId(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar Lanche com Id: " + id));
    }

    @GetMapping("/listarPorDescricao/{descricao}")
    public Lanche listarPorDescricao(@PathVariable String descricao) {
        return lancheService.listarPorDescricao(descricao).orElseThrow(() -> new RuntimeException("Não foi possível encontrar Lanche com Descrição: " + descricao));
    }

    @DeleteMapping("/removerPorId/{id}")
    public void remover(@PathVariable long id) {
        lancheService.remover(id);
    }

    @DeleteMapping
    public void removerTodos() {
        lancheService.removerTodos();
    }
}