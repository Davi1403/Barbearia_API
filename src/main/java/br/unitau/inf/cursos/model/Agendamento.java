
package br.unitau.inf.cursos.model;

import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "datahora")
    private LocalDateTime dataHora;
    private String status;

    // -- RELACIONAMENTO COM CLIENTE --

    @ManyToOne // Muitos agendamentos para um cliente
    @JoinColumn(name = "clienteid") // Nome da coluna da chave estrangeira
    @JsonBackReference("clienteagendamento")
    private Cliente cliente;

    // -- RELACIONAMENTO COM BARBEIRO --

    @ManyToOne // Muitos agendamentos para um barbeiro
    @JoinColumn(name = "barbeiroid")
    @JsonBackReference("barbeiroagendamento")
    private Barbeiro barbeiro;

    // -- RELACIONAMENTO COM SERVICO

    @ManyToMany
    @JoinTable(
            name = "agendamentoservico", // Nome da tabela de ligacao
            joinColumns = @JoinColumn(name = "agendamentoid"), // FK de agendamento
            inverseJoinColumns = @JoinColumn(name = "servicoid") //FK da outra servico
    )
    @JsonManagedReference("agendamentoservico")
    private List<Servico> servicos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public List<Servico> getServico() {
        return servicos;
    }

    public void setServico(List<Servico> servicos) {
        this.servicos = servicos;
    }
}
