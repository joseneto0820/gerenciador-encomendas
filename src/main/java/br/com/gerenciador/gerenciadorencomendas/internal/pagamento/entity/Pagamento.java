package br.com.gerenciador.gerenciadorencomendas.internal.pagamento.entity;

import br.com.gerenciador.gerenciadorencomendas.domain.enums.FormaPagamento;
import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@Getter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encomenda_id", nullable = false)
    private Encomenda encomenda;

    protected Pagamento() {
    }

    public Pagamento(BigDecimal valor,
                     FormaPagamento formaPagamento,
                     String observacao) {

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O pagamento deve ser maior que zero."
            );
        }

        if (formaPagamento == null) {
            throw new IllegalArgumentException(
                    "A forma de pagamento é obrigatória."
            );
        }

        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.observacao = observacao;
        this.dataPagamento = LocalDateTime.now();
    }

    public void definirEncomenda(Encomenda encomenda) {
        if (encomenda == null) {
            throw new IllegalArgumentException(
                    "A encomenda é obrigatória."
            );
        }

        if (this.encomenda != null && this.encomenda != encomenda) {
            throw new IllegalStateException(
                    "O pagamento já pertence a outra encomenda."
            );
        }

        this.encomenda = encomenda;
    }
}