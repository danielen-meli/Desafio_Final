package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.SellerHistoryByProductsResponseDto;
import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.dto.SellerHistoryTotalSoldResponseDto;
import com.meli.desafio_final.model.Buyer;
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


    /**
     * Get a Buyer based in ID.
     * @param id ad buyer identification number
     * @return the buyer for the requested ID.
     */
    @GetMapping("/buyer/{id}")
    public ResponseEntity<Buyer> getABuyer(@PathVariable long id){
        return ResponseEntity.ok(sellerBuyerHistoryService.getABuyer(id));
    }

    /**
     * Get a List of Buyers.
     * @return the List of all buyers.
     */
    @GetMapping("/buyers")
    public ResponseEntity<List<Buyer>> getBuyersOrderedBuyPurchaseQuantity(@RequestParam("orderBy") String orderBy) {
        return ResponseEntity.ok(sellerBuyerHistoryService.getBuyersOrderedBuyPurchaseQuantity(orderBy));
    }

    /**
     * Get a List of Buyers ordered based in range passed.
     * @param start ad the start value for the range of value already purchased for a buyer
     * @param end ad the end value for the range of value already purchased for a buyer
     * @param orderBy ad how this list wild be ordered by asc or desc
     * @return the List of buyers where buyer total purchase is between start and end.
     */
    @GetMapping("/buyers/between")
    public ResponseEntity<List<Buyer>> getBuyersBetweenValuePurchaseOrderedBuyPurchaseQuantity(
            @RequestParam("start") long start,
            @RequestParam("end") long end,
            @RequestParam("orderBy") String orderBy
    ) {
        return ResponseEntity.ok(sellerBuyerHistoryService.getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(start, end, orderBy));
    }


    // SELLERS PART

    /**
     * Get a List of all history of sellers sold.
     * @return the List of all sellers history of sold.
     */
    @GetMapping("/sellers/all")
    public ResponseEntity<List<SellerHistoryDto>> getAllSellersHistory() {
        return ResponseEntity.ok(sellerBuyerHistoryService.getAllSellersHistory());
    }

    /**
     * Get a List of sellers history based in one specific product.
     * @param id ad the product id to filter history of seller sold.
     * @return the List sellers history based in a specific product.
     */
    @GetMapping("sellers/byProduct/{id}")
    public ResponseEntity<List<SellerHistoryByProductsResponseDto>> getSellersHistoryByProduct(@PathVariable long id) {
        return ResponseEntity.ok(sellerBuyerHistoryService.getSellersHistoryByProduct(id));
    }


    /**
     * Get a List of sellers ordered by total sells of all products.
     * @return the List of sellers history of sold ordered based on total sells.
     */
    @GetMapping("/sellers/totalSold")
    public ResponseEntity<List<SellerHistoryTotalSoldResponseDto>> getSellersOrderedByTotalSold () {
        return ResponseEntity.ok(sellerBuyerHistoryService.getSellersOrderedByTotalSold());
    }

}
