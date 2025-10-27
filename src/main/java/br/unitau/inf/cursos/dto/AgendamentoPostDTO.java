package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.anotations.BarbeiroFK;
import br.unitau.inf.cursos.anotations.ClienteFK;
import br.unitau.inf.cursos.model.Agendamento;
import br.unitau.inf.cursos.model.Barbeiro;
import br.unitau.inf.cursos.model.Cliente;
import br.unitau.inf.cursos.model.Servico;
import br.unitau.inf.cursos.repository.BarbeiroRepository;
import br.unitau.inf.cursos.repository.ClienteRepository;
import br.unitau.inf.cursos.repository.ServicoRepository;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AgendamentoPostDTO {

    @ClienteFK
    @NotNull (message = "O ID do cliente não pode ser nulo")
    Integer clienteId;

    @BarbeiroFK
    @NotNull (message = "O ID do barbeiro não pode ser nulo")
    Integer barbeiroId;

    @NotNull
    @FutureOrPresent(message = "A data do agendamento não pode ser no passodo")
    private LocalDateTime dateHora;

    @NotNull
    @Size(min = 1, message = "O agendamento precisa ter pelo menos um serviço")
    private List<Integer> servicoIds;

    // --GETTES E SETTERS


    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<Integer> getServicoIds() {
        return servicoIds;
    }

    public void setServicoIds(List<Integer> servicoIds) {
        this.servicoIds = servicoIds;
    }

    public Integer getBarbeiroId() {
        return barbeiroId;
    }

    public void setBarbeiroId(Integer barbeiroId) {
        this.barbeiroId = barbeiroId;
    }

    public LocalDateTime getDateHora() {
        return dateHora;
    }

    public void setDateHora(LocalDateTime dateHora) {
        this.dateHora = dateHora;
    }

    public Agendamento convert (ClienteRepository clienteRepository,
                                BarbeiroRepository barbeiroRepository,
                                ServicoRepository servicoRepository){
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(dateHora);

        Optional<Cliente> clienteSearch = clienteRepository.findById(this.clienteId);   // Procura o ID enviado no banco de dados, o Optional permite// que o valor seja um NullPoint sem quebra o código
        clienteSearch.ifPresent(agendamento::setCliente);                               // Caso exista algo dentro do Opcional, ele e usado como atributo no set.

        Optional<Barbeiro> barbeiroSearch = barbeiroRepository.findById(this.barbeiroId);
        barbeiroSearch.ifPresent(agendamento::setBarbeiro);

        List<Servico> servicos = servicoRepository.findAllById(this.servicoIds); // Caso não encontre nada retorna uma lista vazia (N QUEBRA O CÓDIGO)
        agendamento.setServico(servicos);

        return agendamento;
    }
}

