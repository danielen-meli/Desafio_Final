package com.meli.desafio_final.controller.payment;


import com.meli.desafio_final.controller.payment.dto.CustomerCardDto;
import com.meli.desafio_final.controller.payment.service.CardService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.customer.CustomerCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/{customerId}/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping()
    public ResponseEntity<MPResourceList<CustomerCard>> all(@PathVariable String customerId) throws MPException, MPApiException {
        MPResourceList<CustomerCard> customer = cardService.getAllByCustomerId(customerId);
        return ResponseEntity.ok(customer);
    }

    @PostMapping()
    public ResponseEntity<CustomerCard> create(@PathVariable String customerId, @RequestBody CustomerCardDto customerDto) throws MPException, MPApiException {
        CustomerCard customer = cardService.create(customerId, customerDto);
        customer.setResponse(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> delete(@PathVariable String customerId, @PathVariable String cardId) throws MPException, MPApiException {
        cardService.delete(customerId, cardId);
        return ResponseEntity.ok().build();
    }
}
