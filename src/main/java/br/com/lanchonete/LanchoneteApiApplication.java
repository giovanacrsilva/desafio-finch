package br.com.lanchonete;

import br.com.lanchonete.controller.CardapioController;
import br.com.lanchonete.controller.IngredienteController;
import br.com.lanchonete.controller.LancheController;
import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.model.Lanche;
import br.com.lanchonete.service.IngredienteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableCaching
public class LanchoneteApiApplication {

    @Autowired
    private IngredienteServiceImpl ingredienteService;

    @Autowired
    private IngredienteController ingredienteController;

    @Autowired
    private LancheController lancheController;

    @Autowired
    private CardapioController cardapioController;

    public static void main(String[] args) {
        SpringApplication.run(LanchoneteApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            //Ingredientes
            this.ingredienteService.removerTodos();

            ObjectMapper objectMapper = new ObjectMapper();
            Ingrediente ingrediente;
            String json;

            // Salvando via POST
            json = "{\n" +
                    "  \"ingredienteId\": null,\n" +
                    "  \"descricao\": \"Alface\",\n" +
                    "  \"preco\": 0.40,\n" +
                    "  \"cardapios\": []\n" +
                    "}";
            ingrediente = objectMapper.readValue(json, Ingrediente.class);
            ingredienteController.adicionar(ingrediente);

            json = "{\n" +
                    "  \"ingredienteId\": null,\n" +
                    "  \"descricao\": \"Bacon\",\n" +
                    "  \"preco\": 2.00,\n" +
                    "  \"cardapios\": []\n" +
                    "}";
            ingrediente = objectMapper.readValue(json, Ingrediente.class);
            ingredienteController.adicionar(ingrediente);

            // Salvando direto no banco
            this.ingredienteService.salvarTodos(new Ingrediente("Hamburguer", new BigDecimal("3.00")),
                    new Ingrediente("Ovo", new BigDecimal("0.80")),
                    new Ingrediente("Queijo", new BigDecimal("1.50"))
            );

            lancheController.removerTodos();
            cardapioController.removerTodos();

            // Lanches
            Lanche lanche;
            json = "  {\n" +
                    "    \"lancheId\": null,\n" +
                    "    \"descricao\": \"nomeDoLanche\",\n" +
                    "    \"preco\": 0,\n" +
                    "    \"cardapios\": []\n" +
                    "  }";
            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("x-bacon");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("bacon"),
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("queijo")
            );

            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("x-burger");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("queijo")
            );

            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("x-egg");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("ovo"),
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("queijo")
            );

            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("x-egg bacon");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("ovo"),
                    ingredienteController.listarPorDescricao("bacon"),
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("queijo")
            );

            // Lanches promocionais
            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("x-salada");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("alface"),
                    ingredienteController.listarPorDescricao("hamburguer")
            );

            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("tiplo-burguer");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("queijo")
            );

            lanche = objectMapper.readValue(json, Lanche.class);
            lanche.setDescricao("tiplo-queijo");
            cardapioController.montar(lanche,
                    ingredienteController.listarPorDescricao("hamburguer"),
                    ingredienteController.listarPorDescricao("queijo"),
                    ingredienteController.listarPorDescricao("queijo"),
                    ingredienteController.listarPorDescricao("queijo")
            );
        };
    }

}
