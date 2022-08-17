package com.meli.desafio_final.controller.payment;


import com.meli.desafio_final.controller.payment.dto.PaymentDto;
import com.meli.desafio_final.controller.payment.service.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPSearchRequest;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{buyerId}")
    public ResponseEntity<?> creat(@RequestBody PaymentDto paymentDto, @PathVariable Long buyerId) throws MPException, MPApiException {
        Payment payment = paymentService.processPayment(paymentDto, buyerId);
        payment.setResponse(null);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Object> get(@PathVariable Long paymentId) throws MPException, MPApiException {
        Payment payment = paymentService.getPayment(paymentId);
        payment.setResponse(null);
        return ResponseEntity.ok(payment);
    }

}
