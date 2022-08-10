package com.meli.desafio_final.controller;


import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.Warehouse;
import com.meli.desafio_final.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WareHouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping("/{wareHouseId}/{sellerAdId}")
    public ResponseEntity<Long> getTotalQuantitySellerAd(@PathVariable("sellerAdId") SellerAd sellerAdId, @PathVariable("wareHouseId") Warehouse wareHouseId){
        return ResponseEntity.ok(warehouseService.getTotalQuantitySellerAdinWareHouse(sellerAdId, wareHouseId));
    }
}
