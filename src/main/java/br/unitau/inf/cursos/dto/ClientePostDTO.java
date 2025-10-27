package br.unitau.inf.cursos.dto;

import br.unitau.inf.cursos.model.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ClientePostDTO {
    @NotBlank
    @Length(max = 255)
    private String nome;

    @Length(max = 20)
    private String telefone;

    public String getNome(){ return nome;}

    public String getTelefone() { return telefone; }

    public Cliente convert(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        return cliente;
    }

}
