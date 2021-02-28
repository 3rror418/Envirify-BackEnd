package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.services.EnvirifyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EnvirifyExceptionHandler {

    @ExceptionHandler(EnvirifyException.class)
    private ResponseEntity<?> exceptionHandler(EnvirifyException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
