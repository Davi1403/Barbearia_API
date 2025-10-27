package br.unitau.inf.cursos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity // Define que essa classe se refere a uma tabrla
public class Cliente{
    @Id // Define que é uma PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Integer id;
    String nome;
    String telefone;

    @JsonManagedReference("clienteagendamento")
    @OneToMany(mappedBy = "cliente") // Um clinte pode ter varios agendamentos
    private List<Agendamento> agendamentos;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public List<Agendamento> getAgendamentos(){return agendamentos;}
    public void setAgendamentos(List<Agendamento> agendamentos) {this.agendamentos = agendamentos;}

}
