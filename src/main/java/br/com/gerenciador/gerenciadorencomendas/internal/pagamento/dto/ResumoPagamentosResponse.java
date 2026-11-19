package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto;

import java.math.BigDecimal;
import java.util.List;

public record ResumoPagamentosResponse(
        Long encomendaId,
        BigDecimal valorTotal,
        BigDecimal totalPago,
        BigDecimal saldoRestante,
        boolean quitada,
        List<PagamentoResponse> pagamentos
) {
}