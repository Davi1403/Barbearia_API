package br.unitau.inf.cursos.controller;


import br.unitau.inf.cursos.dto.ClienteGetDTO;
import br.unitau.inf.cursos.dto.ClientePostDTO;
import br.unitau.inf.cursos.dto.ClientePutDTO;
import br.unitau.inf.cursos.model.Agendamento;
import br.unitau.inf.cursos.model.Cliente;
import br.unitau.inf.cursos.repository.AgendamentoRepository;
import br.unitau.inf.cursos.repository.ClienteRepository;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.beans.Transient;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController // Define que a classe é um controller de API REST
@RequestMapping("/cliente") // Define a url do endpoint

public class ClienteController {

    // -- CRIO UMA INSTÂNCIA DO MEU REPOSITÓRIO CLIENTE PARA PODER FAZER AS OPERAÇÕES --
    @Autowired
    private ClienteRepository repositoryCliente;

    // -- REGRA DE NEGÓCIO : NÃO POSSO DELETAR UM CLIENTE COM AGENDAMENTOS --
    @Autowired
    private AgendamentoRepository repositoryAgendamento;

    // -- ENDPOINTS --

    // -- GET TODOS OS CLIENTES --
    @GetMapping
    public List<ClienteGetDTO> get(){
        List<Cliente> lista = repositoryCliente.findAll();
        return ClienteGetDTO.convert(lista);
    }

    // -- GET ByID --

    @GetMapping("/{id}")

    // ReposnseEntenty é um "envelope", um resposta http tem várias partes (Body, Status, Headers)
    // @PathVariable Integer id --> Pega o valor que vêm na {id}

    public ResponseEntity<Cliente> getById(@PathVariable Integer id){
        ResponseEntity<Cliente> ret = ResponseEntity.notFound().build(); // Define o return como ERRO 404 por padrao.
        Optional<Cliente> search = repositoryCliente.findById(id);

        if (search.isPresent()){           // SE ENCONTRAR UM CLIENTE
            Cliente item = search.get();   // Pega o objeto dentro do search
            ret = ResponseEntity.ok(item); // Define o return como 200 (ok) e passo o objeto para ele.
        }else {
            System.out.println("CLiente não encontrado");
        }
        return ret;
    }

    // -- POST --

    @PostMapping
    @Transactional // Garente a Atomicidade do banco de dodos

    // @RequestBody converte o Json para um objeto do tipo ClientePostDTO
    // @ Valid verifica as validações do post. Ex: @NotBlank

    public ResponseEntity<ClienteGetDTO> post (@RequestBody @Valid ClientePostDTO body, UriComponentsBuilder uriBuilder){
        ResponseEntity<ClienteGetDTO> ret = ResponseEntity.unprocessableEntity().build(); // Define o return como ERRO 422
        Cliente item = body.convert(); // Cria um entidade
        Optional<Cliente> search = repositoryCliente.findByNome(item.getNome()); // Busca por esse nome no repositorio

        if (!search.isPresent()){ // SE O NOME NÃO EXISTIR
            repositoryCliente.save(item); // Salva no BD
            URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri(); // Cria a nova URL
            ret = ResponseEntity.created(uri).body(new ClienteGetDTO(item)); // Define o return como sucesso 201
        }else {
            System.out.println("Nome do cliente já existe!");
        }
        return ret;
    }

    // -- PUT --

    @PutMapping("/{id}")
    @Transactional

    public ResponseEntity<ClienteGetDTO> put (@PathVariable("id") int id, @RequestBody @Valid ClientePutDTO body, UriComponentsBuilder uriBuilder){
        ResponseEntity<ClienteGetDTO> ret = ResponseEntity.notFound().build(); // Define o return como ERRO 404 Not Found
        Optional<Cliente> search = repositoryCliente.findById(id); // Busca pelo ID

        if (search.isPresent()){

            Cliente item = search.get(); // Perfil que estou alterando
            boolean found = false;       // Found = Outra id com mesmo nome
            Optional<Cliente> other = repositoryCliente.findByNome(body.getNome()); // Perfil com mesmo nome do body

            /*
                REGRA DE NEGÓCIO : NÃO POSSO TER DOIS CLIENTE COM MESMO NOME
                Existe dois casos para clientes com mesmo nome:

                Caso 1:

                NOME QUE ESTÁ NO BODY, JÁ ESTÁ SENDO USADO POR OUTRO ID

                Ex:
                Item é (Aluno 5, Nome "Davi")
                Body.getNome = "Maria"
                repository.findByNome("Maria") --> Existe Maria id = 2

                if (2 != 5)
                    retorna o erro 422

               Caso 2:

               O NOME QUE ESTÁ NO BODY ESTÁ SENDO UTILIZADO PELO MESMO ID QUE ESTAMOS EDITANDO. EX: SÓ
               ALTEREI O TELEFONE, O NOME CONTINUA IGUAL.

                Item é (Aluno 5, Nome "Davi")
                Body.getNome = "Davi"
                repository.findByNome("Davi") --> Existe Davi id = 5

                if (2 != 5)
                    retorna o erro 422
                    O código continua

             */

            if (other.isPresent()){
                Cliente mesmoNome = other.get();

                if (mesmoNome.getId() != item.getId()){
                    found = true;
                    System.out.println("Esse nome já está sendo usado pelo cliete (id = " + mesmoNome.getId() + ")");
                    ret = ResponseEntity.unprocessableEntity().build(); // 422 Unprocessable Entity (erro de regra de negócio).
                }
            }
            if (!found){
                body.update(item); // Atualiza o Item com elementos do Body.
                URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(item.getId()).toUri();
                ret = ResponseEntity.created(uri).body(new ClienteGetDTO(  item));
            }
        }else{
            System.out.println("Aluno não encontrado");
        }
        return ret;
    }

    // -- DELETE --

    @DeleteMapping("/{id}")
    @Transactional

    public ResponseEntity<?> delete (@PathVariable("id") int id){ // A resposta não tem body, por isso o <?>
        ResponseEntity<Cliente> ret = ResponseEntity.notFound().build(); // Define o return como ERRO 404 Not Found
        Optional<Cliente> search = repositoryCliente.findById(id);

        if (search.isPresent()){
            Cliente item = search.get();
            List<Agendamento> agendamentos = repositoryAgendamento.findByCliente_id(id);

            if (agendamentos.size() == 0){
                repositoryCliente.delete(item);
                ret = ResponseEntity.ok().build(); // sucesso 200
            }else{
                System.out.println("Não é possivel excluir clientes com agendamentos");
                ret = ResponseEntity.unprocessableEntity().build(); // 422 Unprocessable Entity
            }

        }else {
            System.out.println("Aluno não encontrado");
        }
        return ret;
    }
}
