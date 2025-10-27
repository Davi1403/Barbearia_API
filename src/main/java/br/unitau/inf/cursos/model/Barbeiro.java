package br.unitau.inf.cursos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String nome;
    String endereco;
    String telefone;

    @JsonManagedReference("barbeiroagendamento")
    @OneToMany(mappedBy = "barbeiro")
    private List<Agendamento> agendamentos;

    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getEndereco(){return endereco;}
    public void setEndereco(String endereco){this.endereco = endereco;}

    public String getTelefone(){return telefone;}
    public void setTelefone(String telefone){this.telefone = telefone;}

    public List<Agendamento> getAgendamentos(){return agendamentos;}
    public void setAgendamentos(List<Agendamento> agendamentos){this.agendamentos = agendamentos;}

}
