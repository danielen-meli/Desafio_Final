package com.meli.desafio_final.controller.payment;

import com.meli.desafio_final.dto.payment.CustomerDto;
import com.meli.desafio_final.service.payment.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments/clients")
public class ClientController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Object> creatClient(@RequestBody CustomerDto customerDto) throws MPException, MPApiException {
        Customer customer = paymentService.creatClient(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDto(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable String id) throws MPException, MPApiException {
        Customer customer = paymentService.getClientById(id);
        return ResponseEntity.ok(new CustomerDto(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable String id, @RequestBody CustomerDto customerDto) throws MPException, MPApiException {
        Customer customer = paymentService.updateClient(id, customerDto);
        return ResponseEntity.ok(new CustomerDto(customer));
    }
}
