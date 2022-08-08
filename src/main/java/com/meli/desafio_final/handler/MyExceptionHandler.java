package com.meli.desafio_final.handler;

import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.exception.ExceptionsDetails;
import com.meli.desafio_final.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionsDetails> notFoundException(NotFoundException ex){
        return new ResponseEntity<>(ExceptionsDetails.builder()
                .erro("Not Found!")
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .statusHttp(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionsDetails> badRequestException(BadRequestException ex){
        return new ResponseEntity<>(ExceptionsDetails.builder()
                .erro("Invalid Argument")
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .statusHttp(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
