package br.com.lanchonete.service;

import br.com.lanchonete.model.Lanche;
import br.com.lanchonete.repository.LancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancheServiceImpl implements LancheService {

    @Autowired
    private LancheRepository lancheRepository;

    @Override
    public List<Lanche> listarTodos() {
        return lancheRepository.findAll();
    }

    @Override
    public Optional<Lanche> listarPorId(long id) {
        return lancheRepository.findById(id);
    }

    @Override
    public Optional<Lanche> listarPorDescricao(String descricao) {
        return lancheRepository.findByDescricao(descricao);
    }

    @Override
    public void salvar(Lanche lanche) {
        lancheRepository.save(lanche);
    }

    @Override
    public void remover(long id) {
        lancheRepository.deleteById(id);
    }

    @Override
    public void removerTodos() {
        lancheRepository.deleteAll();
    }
}