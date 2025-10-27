package br.unitau.inf.cursos.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.unitau.inf.cursos.validator.ClienteFKValidator;

@Constraint(validatedBy = ClienteFKValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteFK {
    String message() default "Cliente invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
