package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ClientePutDTO {

    @Length(max = 255)
    private String nome;

    @Length(max = 20)
    private String telefone;

    public String getNome(){ return nome;}

    public String getTelefone() { return telefone; }

    public void update(Cliente cliente){
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
    }
}
