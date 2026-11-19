package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto;

import br.com.gerenciador.gerenciadorencomendas.domain.enums.FormaPagamento;

import java.math.BigDecimal;

public record RegistrarPagamentoRequest(
        BigDecimal valor,
        FormaPagamento formaPagamento,
        String observacao
) {
}