package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.controller;

import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaRequestDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.dto.EncomendaResponseDto;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.service.EncomendaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/encomendas")
public class EncomendaController {

    private EncomendaService encomendaService;

    public EncomendaController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @PostMapping
    public ResponseEntity<EncomendaResponseDto> cadastrar(
            @RequestBody EncomendaRequestDto dados
    ) {

        EncomendaResponseDto resposta =
                encomendaService.cadastrar(dados);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }
}