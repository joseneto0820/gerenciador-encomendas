package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.controller;

import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto.PagamentoResponse;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto.RegistrarPagamentoRequest;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto.ResumoPagamentosResponse;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encomendas/{encomendaId}/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoResponse registrar(
            @PathVariable("encomendaId") Long encomendaId,
            @RequestBody RegistrarPagamentoRequest request
    ) {
        return pagamentoService.registrar(encomendaId, request);
    }

    @GetMapping
    public ResumoPagamentosResponse consultar(
            @PathVariable("encomendaId") Long encomendaId
    ) {
        return pagamentoService.consultar(encomendaId);
    }
}