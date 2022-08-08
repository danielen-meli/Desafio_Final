package com.meli.desafio_final.exception;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ExceptionsDetails{
        private String erro;
        private int statusHttp;
        private String message;

}