package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto;

import java.time.LocalDate;

public record EncomendaRequestDto(
        Long clienteId,
        LocalDate prazoEntrega,
        String observacoes
) {
}