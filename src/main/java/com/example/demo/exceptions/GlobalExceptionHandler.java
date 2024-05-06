package com.example.demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error: " + ex.getMessage());

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}