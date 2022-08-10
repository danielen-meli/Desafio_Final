package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.ShopOrderDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.service.IShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class ShopOrderController{
    @Autowired
    private IShopOrderService shopOrderService;

    @Autowired
    private IBatchStockRepository testerepo;

    @GetMapping("/{id}")
    private ShopOrderDto getById(@PathVariable long id){
        ShopOrder shop = shopOrderService.getById(id);
        return new ShopOrderDto(shop);
    }

    @PostMapping()
    public ResponseEntity<ShopOrderResponseDto> createShopOrder(@RequestBody ShopOrderRequestDto shopOrderRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOrderService.insertNewShopOrder(shopOrderRequestDto));
    }

    @PutMapping("/closed-shopOrder/{id}")
    public ResponseEntity<ShopOrder> closeShopOrder(@PathVariable long id){
        return ResponseEntity.ok(shopOrderService.closedShopOrder(id));

    }
}
