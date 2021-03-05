package edu.eci.ieti.envirify.exceptions;

import org.springframework.http.HttpStatus;

public class EnvirifyException extends Exception {

    private final HttpStatus status;

    public EnvirifyException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public EnvirifyException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
