package com.meli.desafio_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
public class QuantityException extends RuntimeException {
    public QuantityException(String message) {
        super(message);
    }
}