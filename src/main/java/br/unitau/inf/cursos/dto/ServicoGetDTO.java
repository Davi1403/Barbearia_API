package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Servico;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoGetDTO {
    private Integer id;
    private String tipo;
    private String descricao;
    private BigDecimal valor;

    public ServicoGetDTO(Servico servico){
        this.id = servico.getId();
        this.tipo = servico.getTipo();
        this.descricao = servico.getTipo();
        this.valor = servico.getValor();
    }

    public Integer getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public String getDescricao(){
        return descricao;
    }

    public BigDecimal getValor(){
        return valor;
    }

    public static List<ServicoGetDTO> convert (List<Servico> servicos){
        return servicos.stream().map(ServicoGetDTO::new).collect(Collectors.toList());
    }

}
