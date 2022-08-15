package com.meli.desafio_final.controller;

import com.meli.desafio_final.model.PromoCode;
import com.meli.desafio_final.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoCodeController {

    @Autowired
    PromoCodeService promoCodeService;

    @PostMapping("/new")
    public ResponseEntity<PromoCode> registerNewPromo(@RequestBody @Valid PromoCode newPromoCode){
        return ResponseEntity.status(HttpStatus.CREATED).body(promoCodeService.registerNewPromo(newPromoCode));
    }

//    @GetMapping("/getDiscount")
//    public ResponseEntity<PromoCode> getDiscount(){
//        return null;
//    } //esse endpoint provavelmente vai ficar em shop order para fechar o carrinho com desconto.
}
