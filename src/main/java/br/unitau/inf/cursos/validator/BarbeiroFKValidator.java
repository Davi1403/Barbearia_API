package br.unitau.inf.cursos.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.unitau.inf.cursos.model.Barbeiro;
import br.unitau.inf.cursos.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import br.unitau.inf.cursos.anotations.BarbeiroFK;

public class BarbeiroFKValidator implements ConstraintValidator<BarbeiroFK, Integer> {
	@Autowired
	private BarbeiroRepository repository;

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value != null) {
			Optional<Barbeiro> search = repository.findById(value);
			if (search.isPresent()) {
				return true;
			}
		}
		return false;
	}
}