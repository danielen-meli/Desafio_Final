package com.meli.desafio_final.controller.payment.dto;

import com.mercadopago.client.common.IdentificationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationRequestDto {
    private String type;
    private String number;
}
