package com.meli.desafio_final.controller.payment.service;

import com.meli.desafio_final.controller.payment.dto.PaymentDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPSearchRequest;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    public PaymentService() {
        MercadoPagoConfig.setAccessToken("TEST-3400945058736804-081210-23eb23642d6ccff5d60584729fd2488f-1006640405");
    }

    public Payment getPayment(Long paymentId) throws MPException, MPApiException {
        return new PaymentClient().get(paymentId);
    }

    public Payment processPayment(PaymentDto paymentDto) throws MPException, MPApiException {

        PaymentPayerRequest paymentPayer = PaymentPayerRequest.builder()
                .email(paymentDto.getPayer().getEmail()).build();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(paymentDto.getTransactionAmount())
                        .token(paymentDto.getToken())
                        .description(paymentDto.getDescription())
                        .installments(paymentDto.getInstallments())
                        .paymentMethodId(paymentDto.getPaymentMethodId())
                        .payer(paymentPayer)
                        .build();

       return new PaymentClient().create(paymentCreateRequest);

    }



}
