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
}