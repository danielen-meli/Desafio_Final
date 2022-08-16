package com.meli.desafio_final.dto.payment;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.customer.CustomerRequest;
import com.mercadopago.resources.common.Identification;
import com.mercadopago.resources.common.Phone;
import com.mercadopago.resources.customer.Customer;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Phone phone;
    private Identification identification;
    private String defaultCard;

    public CustomerDto(Customer customer){
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phone = customer.getPhone();
        this.identification = customer.getIdentification();
        this.defaultCard = customer.getDefaultCard();
    }

    public CustomerRequest convert(){
        PhoneRequest phone = PhoneRequest.builder()
                .areaCode(this.phone.getAreaCode())
                .number(this.phone.getNumber())
                .build();

        IdentificationRequest identificationRequest = IdentificationRequest.builder()
                .number(this.identification.getNumber())
                .type(this.identification.getType())
                .build();

        return CustomerRequest.builder()
                .identification(identificationRequest)
                .defaultCard(this.defaultCard)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phone(phone)
                .identification(identificationRequest)
                .build();
    }
}
