package br.unitau.inf.cursos.repository;

import br.unitau.inf.cursos.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Integer>{
}
