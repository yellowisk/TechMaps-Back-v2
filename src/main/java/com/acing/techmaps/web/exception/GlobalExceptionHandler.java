package com.acing.techmaps.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * Handles all errors thrown at runtime, returning a specific {@link ResponseEntity}.
 * Can be extended accordingly.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Map<String, String>> handleException(HttpException exception) {
        return new ResponseEntity<>(exception.toMap(), exception.getStatus());
    }
}