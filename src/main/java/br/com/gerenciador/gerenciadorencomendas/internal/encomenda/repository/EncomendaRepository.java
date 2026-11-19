package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.repository;

import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncomendaRepository
        extends JpaRepository<Encomenda, Long> {

    List<Encomenda> findByCliente_Id(Long clienteId);

}