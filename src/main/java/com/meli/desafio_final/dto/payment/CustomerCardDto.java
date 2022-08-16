package com.meli.desafio_final.dto.payment;

import com.mercadopago.client.customer.CustomerCardCreateRequest;
import com.mercadopago.resources.customer.CustomerCardIssuer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCardDto {
    private String token;
    private CustomerCardIssuer issuer;
    private String paymentMethodId;

    public CustomerCardCreateRequest convert() {
        return CustomerCardCreateRequest.builder()
                .token(this.token)
                .issuer(this.issuer)
                .paymentMethodId(this.paymentMethodId)
                .build();
    }
}
