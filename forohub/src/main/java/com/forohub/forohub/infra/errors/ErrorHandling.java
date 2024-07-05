package com.forohub.forohub.infra.errors;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){

        var errors=e.getFieldErrors().stream().map(ValidationError::new).toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity handleErrorIntegrity(Exception e){

        var error=new ValidationError("Parameter",e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleErrorValidation(ValidationException e){

        var error=new ValidationError("Parameter",e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    private record ValidationError(String field, String message) {

        public ValidationError(FieldError error){

            this(error.getField(), error.getDefaultMessage());
        }

    }



}
