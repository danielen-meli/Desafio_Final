package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.service.SellerAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SellerAdController {

    @Autowired
    private SellerAdService sellerAdService;

    @GetMapping("/fresh-products")
    public ResponseEntity<List<SellerAdDTO>> getListProducts(){
        if(sellerAdService.getAllProducts().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sellerAdService.getAllProducts());
    }

    @GetMapping("/fresh-products/category")
    public ResponseEntity<List<SellerAdDTO>> getProductsCategory(@RequestParam("category") Category category){
        if(sellerAdService.getByCategory(category).isEmpty()){
            return ResponseEntity.notFound().build();
        }//TODO paula: passar verificacao para service
        return ResponseEntity.ok(sellerAdService.getByCategory(category));
    }
}
