package com.ramirezmontoya.tvmaze_middleware.exception;

public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String message) {
        super(message);
    }
}
