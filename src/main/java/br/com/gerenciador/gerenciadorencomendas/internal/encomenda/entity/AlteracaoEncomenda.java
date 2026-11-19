
package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alteracoes_encomenda")
@Getter
public class AlteracaoEncomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String descricao;

    private LocalDateTime dataAlteracao;

    private String responsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encomenda_id", nullable = false)
    private Encomenda encomenda;

    protected AlteracaoEncomenda() {
    }

    public AlteracaoEncomenda(String descricao, String responsavel) {
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.dataAlteracao = LocalDateTime.now();
    }

    void definirEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }
}
