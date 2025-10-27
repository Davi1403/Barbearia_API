package br.unitau.inf.cursos.repository;

import br.unitau.inf.cursos.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{
    List<Agendamento> findByCliente_id(Integer curso_id);
}
