
package br.com.gerenciador.gerenciadorencomendas.domain.entity;

import br.com.gerenciador.gerenciadorencomendas.domain.enums.StatusEncomenda;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "encomendas")
@Getter
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDate prazoEntrega;

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusEncomenda status;

    @Column(length = 1000)
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "encomenda",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemEncomenda> itens = new ArrayList<>();

    @OneToMany(mappedBy = "encomenda",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<AlteracaoEncomenda> alteracoes = new ArrayList<>();

    @OneToMany(mappedBy = "encomenda",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pagamento> pagamentos = new ArrayList<>();

    protected Encomenda() {
    }

    public Encomenda(Cliente cliente, LocalDate prazoEntrega,
                     String observacoes) {
        this.cliente = cliente;
        this.prazoEntrega = prazoEntrega;
        this.observacoes = observacoes;
        this.dataCriacao = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
        this.status = StatusEncomenda.RECEBIDA;
    }

    public void iniciarProducao() {
        if (status != StatusEncomenda.RECEBIDA &&
                status != StatusEncomenda.AGUARDANDO_MATERIAL &&
                status != StatusEncomenda.EM_ANALISE) {
            throw new IllegalStateException(
                    "Não é possível iniciar a produção."
            );
        }

        this.status = StatusEncomenda.EM_PRODUCAO;
    }

    public void marcarComoPronta() {
        if (status != StatusEncomenda.EM_PRODUCAO) {
            throw new IllegalStateException(
                    "A encomenda precisa estar em produção."
            );
        }

        this.status = StatusEncomenda.PRONTA;
    }

    public void registrarAlteracao(AlteracaoEncomenda alteracao) {
        if (status == StatusEncomenda.ENTREGUE ||
                status == StatusEncomenda.CANCELADA) {
            throw new IllegalStateException(
                    "Não é possível alterar esta encomenda."
            );
        }

        alteracoes.add(alteracao);
        alteracao.definirEncomenda(this);
    }

    public void adicionarItem(ItemEncomenda item) {
        itens.add(item);
        item.definirEncomenda(this);
        recalcularValorTotal();
    }

    public void registrarPagamento(Pagamento pagamento) {
        if (pagamento.getValor().compareTo(getSaldoRestante()) > 0) {
            throw new IllegalArgumentException(
                    "O pagamento ultrapassa o saldo restante."
            );
        }

        pagamentos.add(pagamento);
        pagamento.definirEncomenda(this);
    }

    public BigDecimal calcularTotalPago() {
        return pagamentos.stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSaldoRestante() {
        return valorTotal.subtract(calcularTotalPago());
    }

    public boolean estaQuitada() {
        return getSaldoRestante().compareTo(BigDecimal.ZERO) == 0;
    }

    private void recalcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(item -> item.getValorUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void atualizarPrazo(LocalDate novoPrazo) {
        if (status == StatusEncomenda.ENTREGUE ||
                status == StatusEncomenda.CANCELADA) {
            throw new IllegalStateException(
                    "Não é possível atualizar o prazo."
            );
        }

        this.prazoEntrega = novoPrazo;
    }
}
