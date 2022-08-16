package com.meli.desafio_final.controller.payment.dto;

import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.resources.payment.PaymentAdditionalInfo;
import com.mercadopago.resources.payment.PaymentPayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private PaymentAdditionalInfo additionalInfo;
    private Integer installments;
    private String issuerId;
    private PaymentPayer payer;
    private String description;
    private PaymentOrderRequest order;
    private String paymentMethodId;
    private String token;
    private BigDecimal transactionAmount;

    public PaymentCreateRequest convert(){
        List<PaymentItemRequest> items = new ArrayList<>();

        PaymentItemRequest item =
                PaymentItemRequest.builder()
                        .id(additionalInfo.getItems().get(0).getId())
                        .title(additionalInfo.getItems().get(0).getTitle())
                        .description(additionalInfo.getItems().get(0).getDescription())
                        .pictureUrl(
                                additionalInfo.getItems().get(0).getPictureUrl())
                        .categoryId(additionalInfo.getItems().get(0).getCategoryId())
                        .quantity(additionalInfo.getItems().get(0).getQuantity())
                        .unitPrice(additionalInfo.getItems().get(0).getUnitPrice())
                        .build();

        items.add(item);

        PaymentAdditionalInfoPayerRequest payer = PaymentAdditionalInfoPayerRequest.builder()
                .firstName(additionalInfo.getPayer().getFirstName())
                .lastName(additionalInfo.getPayer().getLastName())
                .phone(
                        PhoneRequest.builder().areaCode(additionalInfo.getPayer().getPhone().getAreaCode()).number(additionalInfo.getPayer().getPhone().getNumber()).build())
                .build();

        PaymentAdditionalInfoRequest PaymentAdditionalInfo = PaymentAdditionalInfoRequest.builder()
                .items(items)
                .payer(
                        payer)
                .build();

        PaymentPayerRequest paymentPayer = PaymentPayerRequest.builder()
                .entityType(this.payer.getEntityType())
                .type(this.payer.getType())
                .email(this.payer.getEmail())
                .build();

        return PaymentCreateRequest.builder()
                        .issuerId(this.issuerId)
                        .additionalInfo(
                                PaymentAdditionalInfo)
                        .description(this.description)
                        .installments(this.installments)
                        .payer(paymentPayer)
                        .paymentMethodId(this.paymentMethodId)
                        .token(this.token)
                        .transactionAmount(this.transactionAmount)
                        .build();
    }
}
