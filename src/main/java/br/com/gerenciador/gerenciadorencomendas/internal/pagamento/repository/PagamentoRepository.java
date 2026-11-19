package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.repository;

import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}