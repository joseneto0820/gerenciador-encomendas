package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto;

import br.com.gerenciador.gerenciadorencomendas.domain.enums.StatusEncomenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record EncomendaResponseDto(
        Long id,
        LocalDateTime dataCriacao,
        LocalDate prazoEntrega,
        BigDecimal valorTotal,
        StatusEncomenda status,
        String observacoes,
        Long clienteId
) {
}