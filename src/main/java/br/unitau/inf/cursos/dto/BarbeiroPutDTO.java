package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Barbeiro;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class BarbeiroPutDTO {

    @Length(max = 255)
    private String nome;

    @Length(max = 255)
    private String endereco;

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

    public void update(Barbeiro barbeiro){
        barbeiro.setNome(this.nome);
        barbeiro.setEndereco(this.endereco);
        barbeiro.setTelefone(this.telefone);
    }
}
