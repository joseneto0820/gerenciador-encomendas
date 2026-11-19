
package br.com.gerenciador.gerenciadorencomendas.internal.cliente.entity;

import br.com.gerenciador.gerenciadorencomendas.internal.encomenda.entity.Encomenda;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    private String email;

    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "cliente")
    private List<Encomenda> encomendas = new ArrayList<>();

    protected Cliente() {
    }

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataCadastro = LocalDateTime.now();
    }

    public void atualizarTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void atualizarEmail(String email) {
        this.email = email;
    }
}
