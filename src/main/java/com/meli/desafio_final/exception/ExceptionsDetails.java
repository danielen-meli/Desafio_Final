package com.meli.desafio_final.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionsDetails {
    private String erro;
    private int statusHttp;
    private String message;
    private LocalDateTime dateTime;
}

