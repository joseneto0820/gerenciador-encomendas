package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.service;

import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.repository.EncomendaRepository;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto.PagamentoResponse;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto.RegistrarPagamentoRequest;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.dto.ResumoPagamentosResponse;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.entity.Pagamento;
import br.com.gerenciador.gerenciadorencomendas.internal.pagamento.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final EncomendaRepository encomendaRepository;
    private final PagamentoRepository pagamentoRepository;

    @Transactional
    public PagamentoResponse registrar(
            Long encomendaId,
            RegistrarPagamentoRequest request
    ) {
        Encomenda encomenda = encomendaRepository
                .buscarPorIdParaPagamento(encomendaId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Encomenda não encontrada."
                ));

        Pagamento pagamento;

        try {
            pagamento = new Pagamento(
                    request.valor(),
                    request.formaPagamento(),
                    request.observacao()
            );

            encomenda.registrarPagamento(pagamento);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage(),
                    exception
            );
        } catch (IllegalStateException exception) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    exception.getMessage(),
                    exception
            );
        }

        pagamentoRepository.saveAndFlush(pagamento);

        return PagamentoResponse.de(pagamento);
    }

    @Transactional(readOnly = true)
    public ResumoPagamentosResponse consultar(Long encomendaId) {
        Encomenda encomenda = encomendaRepository.findById(encomendaId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Encomenda não encontrada."
                ));

        var pagamentos = encomenda.getPagamentos().stream()
                .map(PagamentoResponse::de)
                .toList();

        return new ResumoPagamentosResponse(
                encomenda.getId(),
                encomenda.getValorTotal(),
                encomenda.calcularTotalPago(),
                encomenda.getSaldoRestante(),
                encomenda.estaQuitada(),
                pagamentos
        );
    }
}