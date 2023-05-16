package com.mongologgerapi.infra;

import com.mongologgerapi.domain.dto.output.SimpleErrorDTO;
import com.mongologgerapi.domain.exception.LogNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(LogNotFoundException.class)
    public ResponseEntity logNotFound(LogNotFoundException ex) {
        return ResponseEntity.badRequest().body(new SimpleErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(new SimpleErrorDTO(ex.getMessage()));
    }
}
