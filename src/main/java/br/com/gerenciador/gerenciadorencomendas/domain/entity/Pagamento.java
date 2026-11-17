
package br.com.gerenciador.gerenciadorencomendas.domain.entity;

import br.com.gerenciador.gerenciadorencomendas.domain.enums.FormaPagamento;

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

        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.observacao = observacao;
        this.dataPagamento = LocalDateTime.now();
    }

    void definirEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }
}
