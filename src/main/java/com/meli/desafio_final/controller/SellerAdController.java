package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.service.ISellerAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SellerAdController {

    @Autowired
    private ISellerAdService sellerAdService;

    /**
     * Show a list of products (advertisement) to the client.
     * @return List of all products available
     */
    @GetMapping("/fresh-products")
    public ResponseEntity<List<SellerAdDTO>> getListProducts(){
        return ResponseEntity.ok(sellerAdService.getAllProducts());
    }

    /**
     * Show a list of products selected by category (advertisement) to the client.
     * @param category
     * @return List of all products selected by category are available
     */
    @GetMapping("/fresh-products/category")
    public ResponseEntity<List<SellerAdDTO>> getProductsCategory(@RequestParam("category") Category category){
        return ResponseEntity.ok(sellerAdService.getByCategory(category));
    }
}
