package br.unitau.inf.cursos.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.unitau.inf.cursos.model.Cliente;
import br.unitau.inf.cursos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import br.unitau.inf.cursos.anotations.ClienteFK;

public class ClienteFKValidator implements ConstraintValidator<ClienteFK, Integer> {
	@Autowired
	private ClienteRepository repository;

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value != null) {
			Optional<Cliente> search = repository.findById(value);
			if (search.isPresent()) {
				return true;
			}
		}
		return false;
	}
}