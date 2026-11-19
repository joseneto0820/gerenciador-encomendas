package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto;

import br.com.gerenciador.gerenciadorencomendas.domain.enums.FormaPagamento;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.entity.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoResponse(
        Long id,
        BigDecimal valor,
        LocalDateTime dataPagamento,
        FormaPagamento formaPagamento,
        String observacao
) {

    public static PagamentoResponse de(Pagamento pagamento) {
        return new PagamentoResponse(
                pagamento.getId(),
                pagamento.getValor(),
                pagamento.getDataPagamento(),
                pagamento.getFormaPagamento(),
                pagamento.getObservacao()
        );
    }
}