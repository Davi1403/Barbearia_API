package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Servico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ServicoPostDTO {
    @NotBlank
    @Length(max = 100)
    private String tipo;

    @Length(max = 2000)
    private String descricao;

    @NotNull
    private BigDecimal valor;

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Servico convert(){
        Servico servico = new Servico();
        servico.setTipo(this.tipo);
        servico.setDescricao(this.descricao);
        servico.setValor(this.valor);
        return servico;
    }
}
