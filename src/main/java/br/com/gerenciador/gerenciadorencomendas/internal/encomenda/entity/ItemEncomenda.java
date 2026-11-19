
package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_encomenda")
@Getter
public class ItemEncomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeProduto;

    private String descricaoPersonalizacao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encomenda_id", nullable = false)
    private Encomenda encomenda;

    protected ItemEncomenda() {
    }

    public ItemEncomenda(String nomeProduto,
                         String descricaoPersonalizacao,
                         Integer quantidade,
                         BigDecimal valorUnitario) {

        this.nomeProduto = nomeProduto;
        this.descricaoPersonalizacao = descricaoPersonalizacao;
        alterarQuantidade(quantidade);
        alterarValorUnitario(valorUnitario);
    }

    public void alterarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        this.quantidade = quantidade;
    }

    public void alterarPersonalizacao(String descricao) {
        this.descricaoPersonalizacao = descricao;
    }

    public void alterarValorUnitario(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O valor não pode ser negativo."
            );
        }

        this.valorUnitario = valor;
    }

    void definirEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }
}
