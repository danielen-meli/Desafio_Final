package com.meli.desafio_final.controller;

import com.meli.desafio_final.controller.dto.ShopOrderDto;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class ShopOrderController {

    @Autowired
    private ShopOrderService service;

    @GetMapping("/{id}")
    private ShopOrderDto getById(@PathVariable long id){
        ShopOrder shop = service.getById(id);
        return ShopOrderDto.converter(shop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopOrder> updateShopOrder(@PathVariable long id, @RequestBody Map<String, Status> changes){
        return ResponseEntity.ok(service.updatePartial(id, changes));

    }
}
