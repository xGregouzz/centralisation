package com.centralisation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CentralisationException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CentralisationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}