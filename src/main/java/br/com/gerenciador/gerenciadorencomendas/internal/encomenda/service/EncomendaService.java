package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.service;

import br.com.gerenciador.gerenciadorencomendas.internal.cliente.entity.Cliente;
import br.com.gerenciador.gerenciadorencomendas.internal.cliente.repository.ClienteRepository;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaRequestDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaRequestDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaResponseDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaResponseDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.repository.EncomendaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EncomendaService {

    private EncomendaRepository encomendaRepository;
    private ClienteRepository clienteRepository;

    public EncomendaService(
            EncomendaRepository encomendaRepository,
            ClienteRepository clienteRepository
    ) {
        this.encomendaRepository = encomendaRepository;
        this.clienteRepository = clienteRepository;
    }

    public EncomendaResponseDto cadastrar(EncomendaRequestDto dados) {

        Cliente cliente = clienteRepository.findById(dados.clienteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado."
                ));

        Encomenda encomenda = new Encomenda(
                cliente,
                dados.prazoEntrega(),
                dados.observacoes()
        );

        Encomenda salva = encomendaRepository.save(encomenda);

        return new EncomendaResponseDto(
                salva.getId(),
                salva.getDataCriacao(),
                salva.getPrazoEntrega(),
                salva.getValorTotal(),
                salva.getStatus(),
                salva.getObservacoes(),
                salva.getCliente().getId()
        );
    }
}