package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.repository;

import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from Encomenda e where e.id = :id")
    Optional<Encomenda> buscarPorIdParaPagamento(@Param("id") Long id);

    List<Encomenda> findByCliente_Id(Long clienteId);
}
