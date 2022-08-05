package com.meli.desafio_final.controller;

import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.service.SellerAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerAdController {

    @Autowired
    private SellerAdService sellerAdService;

    @GetMapping("/fresh-products")
    public ResponseEntity<List<SellerAd>> getListProducts(){
        if(sellerAdService.getAllProducts().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sellerAdService.getAllProducts());
    }

    @GetMapping("/fresh-products/{category}")
    public ResponseEntity<List<SellerAd>> getProductsCategory(@RequestParam Category category){
        if(sellerAdService.getByCategory(category).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sellerAdService.getByCategory(category));
    }
}
