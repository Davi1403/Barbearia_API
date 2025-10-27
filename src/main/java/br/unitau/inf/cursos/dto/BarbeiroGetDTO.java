package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Agendamento;
import br.unitau.inf.cursos.model.Barbeiro;

import java.util.List;
import java.util.stream.Collectors;

public class BarbeiroGetDTO {
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;

    private List<AgendamentoGetDTO> agendamentos;

    public BarbeiroGetDTO(Barbeiro barbeiro){
        this.id = barbeiro.getId();
        this.nome = barbeiro.getNome();
        this.endereco = barbeiro.getEndereco();
        this.telefone = barbeiro.getTelefone();

        List<Agendamento> agendamentos = barbeiro.getAgendamentos();
        if (agendamentos != null){
            this.agendamentos = AgendamentoGetDTO.convert(agendamentos);
        }
    }

    public Integer getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public String getTelefone(){
        return telefone;
    }

    public List<AgendamentoGetDTO> getAgendamentos(){
        return agendamentos;
    }

    public static List<BarbeiroGetDTO> convert (List<Barbeiro> barbeiros){
        return barbeiros.stream().map(BarbeiroGetDTO::new).collect(Collectors.toList());
    }




}
