package br.com.zupacademy.gabriela.proposal.shared.validation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
public @interface CpfOrCnpj {
    String message() default "Invalid document";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
