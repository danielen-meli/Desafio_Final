package com.meli.desafio_final.controller.payment.service;

import com.meli.desafio_final.controller.payment.dto.PaymentDto;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.service.ShopOrderService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private ShopOrderService shopOrderService;

    public PaymentService() {
        MercadoPagoConfig.setAccessToken("TEST-3400945058736804-081210-23eb23642d6ccff5d60584729fd2488f-1006640405");
    }

    public Payment getPayment(Long paymentId) throws MPException, MPApiException {
        return new PaymentClient().get(paymentId);
    }

    public Payment processPayment(PaymentDto paymentDto, Long buyerId) throws MPException, MPApiException {

        ShopOrder shopOrderByIdClient = shopOrderService.getShopOrderByIdClient(buyerId);

        PaymentPayerRequest paymentPayer = PaymentPayerRequest.builder()
                .email(paymentDto.getPayer().getEmail()).build();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(shopOrderByIdClient.getValorTotal())
                        .token(paymentDto.getToken())
                        .description(paymentDto.getDescription())
                        .installments(paymentDto.getInstallments())
                        .paymentMethodId(paymentDto.getPaymentMethodId())
                        .payer(paymentPayer)
                        .build();



        Payment payment = new PaymentClient().create(paymentCreateRequest);
        if(payment.getStatus().equals("200")){
//        shopOrderService.closedShopOrder(shopOrderByIdClient.getOrderId());
        }
        return payment;
    }
}
