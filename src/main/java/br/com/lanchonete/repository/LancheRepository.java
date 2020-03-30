package br.com.lanchonete.repository;

import br.com.lanchonete.model.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LancheRepository extends JpaRepository<Lanche, Long> {

    Optional<Lanche> findByDescricao(String descricao);
}
