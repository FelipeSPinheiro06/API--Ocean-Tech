package com.oceantech.oceantech.Validation;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
    
    public record ValidationErrorResponse(String campo, String mensagem) {
        public ValidationErrorResponse(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ValidationErrorResponse> validationHandler(MethodArgumentNotValidException exception) {
        return exception
                    .getFieldErrors()
                    .stream()
                    .map(ValidationErrorResponse::new)
                    .toList();
    }
}
