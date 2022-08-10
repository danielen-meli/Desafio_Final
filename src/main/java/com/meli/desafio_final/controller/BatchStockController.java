package com.meli.desafio_final.controller;

import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.enums.OrderBy;
import com.meli.desafio_final.service.IBatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class BatchStockController {

    @Autowired
    IBatchStockService batchStockService;

    @GetMapping("/stock")
    public ResponseEntity<List<BatchStock>> getProductsStock(){
        return ResponseEntity.ok(batchStockService.getProductsInStock());
    }

    @GetMapping("/stock/orderBy/")
    public ResponseEntity<List<BatchStock>> getProductsStockOrder(@RequestParam("orderBy") OrderBy orderBy){
        return null;
    }

}
