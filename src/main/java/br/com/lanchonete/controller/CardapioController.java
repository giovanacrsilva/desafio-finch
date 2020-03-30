package br.com.lanchonete.controller;

import br.com.lanchonete.model.Cardapio;
import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.model.Lanche;
import br.com.lanchonete.service.CardapioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {

    @Autowired
    private CardapioServiceImpl cardapioService;

    @GetMapping
    public List<Cardapio> listarTodos() {
        return cardapioService.listarTodos();
    }

    @GetMapping("/listarPorId/{id}")
    public Cardapio listarPorId(@PathVariable long id) {
        return cardapioService.listarPorId(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar Cardápio com Id: " + id));
    }

    @PostMapping("/montarLanche")
    public void montar(@RequestBody Lanche lanche, @RequestBody Ingrediente... ingredientes) {
        cardapioService.montar(lanche, ingredientes);
    }

    @DeleteMapping("/removerPorId/{id}")
    public void remover(@PathVariable long id) {
        cardapioService.remover(id);
    }

    @DeleteMapping
    public void removerTodos() {
        cardapioService.removerTodos();
    }
}