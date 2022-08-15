package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.SellerHistoryByProductsResponseDto;
import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.dto.SellerHistoryTotalSoldResponseDto;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.Seller;
import com.meli.desafio_final.service.ISellerBuyerHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class SellerBuyerHistoryController {

    @Autowired
    private ISellerBuyerHistoryService sellerBuyerHistoryService;


    @GetMapping("/buyer/{id}")
    public ResponseEntity<Buyer> getABuyer(@PathVariable long id){
        return ResponseEntity.ok(sellerBuyerHistoryService.getABuyer(id));
    }

    @GetMapping("/buyers")
    public ResponseEntity<List<Buyer>> getBuyersOrderedBuyPurchaseQuantity(@RequestParam("orderBy") String orderBy) {
        return ResponseEntity.ok(sellerBuyerHistoryService.getBuyersOrderedBuyPurchaseQuantity(orderBy));
    }

    @GetMapping("/buyers/between")
    public ResponseEntity<List<Buyer>> getBuyersBetweenValuePurchaseOrderedBuyPurchaseQuantity(
            @RequestParam("start") long start,
            @RequestParam("end") long end,
            @RequestParam("orderBy") String orderBy
    ) {
        return ResponseEntity.ok(sellerBuyerHistoryService.getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(start, end, orderBy));
    }


    // SELLERS PART

    @GetMapping("/sellers/all")
    public ResponseEntity<List<SellerHistoryDto>> getAllSellersHistory() {
        return ResponseEntity.ok(sellerBuyerHistoryService.getAllSellersHistory());
    }


    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> getSellersOrderedByTotalSold(@RequestParam("orderBy") String orderBy) {
        return null;
    }

    @GetMapping("sellers/byProduct/{id}")
    public ResponseEntity<List<SellerHistoryByProductsResponseDto>> getSellersHistoryByProduct(@PathVariable long id) {
        return ResponseEntity.ok(sellerBuyerHistoryService.getSellersHistoryByProduct(id));
    }

    @GetMapping("/sellers/totalSold")
    public ResponseEntity<List<SellerHistoryTotalSoldResponseDto>> getSellersOrderedByTotalSold () {
        return ResponseEntity.ok(sellerBuyerHistoryService.getSellersOrderedByTotalSold());
    }

}
