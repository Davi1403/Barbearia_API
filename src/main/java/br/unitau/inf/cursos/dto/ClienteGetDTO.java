package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Agendamento;
import br.unitau.inf.cursos.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteGetDTO {
    private Integer id;
    private String nome;
    private String telefone;

    private List<AgendamentoGetDTO> agendamentos;

    public ClienteGetDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();

        List<Agendamento> agendamentos = cliente.getAgendamentos();
        if (agendamentos!=null){
            this.agendamentos = AgendamentoGetDTO.convert(agendamentos);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<AgendamentoGetDTO> getAgendamentos() {
        return agendamentos;
    }

    public static List<ClienteGetDTO> convert (List<Cliente> lista){
        return lista.stream().map(ClienteGetDTO::new).collect(Collectors.toList());
    }
}
