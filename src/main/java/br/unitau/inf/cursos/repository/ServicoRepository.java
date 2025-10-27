package br.unitau.inf.cursos.repository;

import br.unitau.inf.cursos.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{
}
