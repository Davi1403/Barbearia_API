package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Agendamento;
import br.unitau.inf.cursos.model.Servico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoGetDTO {
    private Integer id;
    private LocalDateTime dataHora ;
    private String status;

    // Estrutura "achatada" de cliente
    private Integer clienteId;
    private String clienteNome;

    // Estrutura "achatada" de barbeiro
    private Integer barbeiroId;
    private String barbeiroNome;

    private List<ServicoGetDTO> servicos;

    public AgendamentoGetDTO(Agendamento agendamento){
        this.id = agendamento.getId();
        this.dataHora = agendamento.getDataHora();
        this.status = agendamento.getStatus();

        if (agendamento.getCliente() != null) {
            this.clienteId = agendamento.getCliente().getId();
            this.clienteNome = agendamento.getCliente().getNome();
        }

        if (agendamento.getBarbeiro() != null) {
            this.barbeiroId = agendamento.getBarbeiro().getId();
            this.barbeiroNome = agendamento.getBarbeiro().getNome();
        }

        List<Servico> servicos = agendamento.getServico();
        if (servicos != null){
            this.servicos = ServicoGetDTO.convert(servicos);
        }
    }

    // GETTERS E SETTERS


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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public Integer getBarbeiroId() {
        return barbeiroId;
    }

    public void setBarbeiroId(Integer barbeiroId) {
        this.barbeiroId = barbeiroId;
    }

    public String getBarbeiroNome() {
        return barbeiroNome;
    }

    public void setBarbeiroNome(String barbeiroNome) {
        this.barbeiroNome = barbeiroNome;
    }

    public List<ServicoGetDTO> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoGetDTO> servicos) {
        this.servicos = servicos;
    }

    // METODO CONVERT

    public static List<AgendamentoGetDTO> convert (List<Agendamento> agendamentos){
        return agendamentos.stream().map(AgendamentoGetDTO::new).collect(Collectors.toList());
    }
}
