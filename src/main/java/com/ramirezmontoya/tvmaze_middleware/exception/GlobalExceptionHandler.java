package com.ramirezmontoya.tvmaze_middleware.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Map<String, String>> handleValidation(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(RestClientException.class)
    ResponseEntity<Map<String, String>> handleExternalApi(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("error", "No se pudo consultar TV Maze"));
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleShowNotFoundException(ShowNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "No se pudo encontrar el objeto buscado"));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(Map.of("error " + HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }
}