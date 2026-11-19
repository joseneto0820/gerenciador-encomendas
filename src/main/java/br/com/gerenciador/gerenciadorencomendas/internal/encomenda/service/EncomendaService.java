package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.service;

import br.com.gerenciador.gerenciadorencomendas.internal.cliente.entity.Cliente;
import br.com.gerenciador.gerenciadorencomendas.internal.cliente.repository.ClienteRepository;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaRequestDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaResponseDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.repository.EncomendaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

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

    // Consultar todas as encomendas
    public List<EncomendaResponseDto> listarTodas() {

        return encomendaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }


    // Consultar encomenda pelo ID
    public EncomendaResponseDto buscarPorId(Long id) {

        Encomenda encomenda = encomendaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Encomenda não encontrada."
                ));

        return converterParaDTO(encomenda);
    }


    // Consultar encomendas de um cliente
    public List<EncomendaResponseDto> buscarPorCliente(Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente não encontrado."
            );
        }

        return encomendaRepository.findByCliente_Id(clienteId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }


    // Converter entidade para DTO
    private EncomendaResponseDto converterParaDTO(Encomenda encomenda) {

        return new EncomendaResponseDto(
                encomenda.getId(),
                encomenda.getDataCriacao(),
                encomenda.getPrazoEntrega(),
                encomenda.getValorTotal(),
                encomenda.getStatus(),
                encomenda.getObservacoes(),
                encomenda.getCliente().getId()
        );
    }
}