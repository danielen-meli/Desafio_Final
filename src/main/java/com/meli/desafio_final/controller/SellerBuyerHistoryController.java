package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.Seller;
import com.meli.desafio_final.model.SellerHistory;
import com.meli.desafio_final.model.enums.Category;
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

    @GetMapping("/sellers/all")
    public ResponseEntity<List<SellerHistoryDto>> getAllSellersHistory() {
        return ResponseEntity.ok(sellerBuyerHistoryService.getAllSellersHistory());
    }


    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> getSellersOrderedByTotalSold(@RequestParam("orderBy") String orderBy) {
        return null;
    }

    @GetMapping("sellers/byProduct/{id}")
    public ResponseEntity<List<Seller>> getSellersHistoryByProduct(@PathVariable long id, @RequestParam("orderBy") String orderBy) {
        // TODO: fazer um Sellect SellerAd where productId = productId da rota, isso
        //  vai retornar uma lista de ids de SellerAd, com isso buscar na tabela de SellerHistory
        //  todos os que tem SellerAdId dessa lista (usar o in) como no exemplo passado para o mauri
        return null;
    }

}
