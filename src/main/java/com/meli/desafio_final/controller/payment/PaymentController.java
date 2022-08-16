package com.meli.desafio_final.controller.payment;


import com.meli.desafio_final.controller.payment.dto.PaymentDto;
import com.meli.desafio_final.controller.payment.service.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> creat(@RequestBody PaymentDto paymentDto) throws MPException, MPApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.processPayment(paymentDto));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Object> get(@PathVariable Long paymentId) throws MPException, MPApiException {
        return ResponseEntity.ok(paymentService.getPayment(paymentId));
    }

}
