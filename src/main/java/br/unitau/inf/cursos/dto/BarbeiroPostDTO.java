package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Barbeiro;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class BarbeiroPostDTO {
    @NotBlank
    @Length(max = 255)
    private String nome;

    @NotBlank
    @Length(max = 255)
    private String endereco;

    @NotBlank
    @Length(max = 20)
    private String telefone;

    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public String getTelefone(){
        return telefone;
    }

    public Barbeiro convert(){
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(this.nome);
        barbeiro.setEndereco(this.endereco);
        barbeiro.setTelefone(this.telefone);
        return barbeiro;
    }
}
