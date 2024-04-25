package com.paul.project.handlers;


import com.paul.project.exceptions.ObjectValidationException;
import com.paul.project.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
@author paul damien..
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // all absorber exceptions
    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException objectValidationException){
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage("Object not validation exceptions not occured")
                .errorSource(objectValidationException.getViolationSource())
                .build();
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionRepresentation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException entityNotFoundException){
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage(entityNotFoundException.getMessage())
                .build();
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionRepresentation);
    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handle(OperationNonPermittedException operationNonPermittedException){
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage(operationNonPermittedException.getErrorMsg())
                .build();
        return  ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(exceptionRepresentation);
    }
}
