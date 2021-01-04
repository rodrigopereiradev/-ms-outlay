package com.rodrigo.msuser.resources.validators;

import com.rodrigo.msuser.exceptions.InvalidArgumentsException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DTOValidator {

    private DTOValidator() {
    }

    public static void validate(Object object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        List<String> messages = violations
                .stream()
                .map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage())
                .collect(Collectors.toList());
        if (!messages.isEmpty())
            throw new InvalidArgumentsException(messages);
    }

}
