package br.com.lanchonete.repository;

import br.com.lanchonete.model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

    List<Optional<Cardapio>> findByLancheDescricao(String descricao);
}
