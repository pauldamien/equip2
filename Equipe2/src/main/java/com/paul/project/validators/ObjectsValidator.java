package com.paul.project.validators;

import com.paul.project.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator <T>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator  validator = factory.getValidator();

    public  void validate(T objectToValidate){

        //validations pour les violations de la classe et cette violation contient des messages
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()){
            Set<String> errorsMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw  new ObjectValidationException(errorsMessages,objectToValidate.getClass().getName());
        }
    }
}
