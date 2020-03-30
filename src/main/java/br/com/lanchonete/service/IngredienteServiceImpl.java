package br.com.lanchonete.service;

import br.com.lanchonete.model.Ingrediente;
import br.com.lanchonete.repository.IngredienteRepository;
import br.com.lanchonete.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }

    @Override
    public Optional<Ingrediente> listarPorId(long id) {
        return ingredienteRepository.findById(id);
    }

    @Override
    public Optional<Ingrediente> listarPorDescricao(String descricao) {
        return ingredienteRepository.findByDescricao(descricao);
    }

    @Override
    public void salvar(Ingrediente ingrediente) {
        ingrediente.setDescricao(StringUtils.unnaccent(ingrediente.getDescricao().toLowerCase()));
        ingredienteRepository.save(ingrediente);
    }

    @Override
    public void salvarTodos(Ingrediente... ingredientes) {
        for (Ingrediente ingrediente : ingredientes) {
            salvar(ingrediente);
        }
    }

    @Override
    public void atualizar(long id, String novoPreco) {
        Ingrediente ingrediente = listarPorId(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o Ingrediente com Id: " + id));
        ingrediente.setPreco(new BigDecimal(novoPreco));
        salvar(ingrediente);
    }

    @Override
    public void removerTodos() {
        ingredienteRepository.deleteAll();
        ingredienteRepository.flush();
    }
}