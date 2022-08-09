package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.service.ShopOrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meli.desafio_final.dto.ShopOrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class ShopOrderController{
    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private IBatchStockRepository testerepo;

    @GetMapping("/{id}")
    private ShopOrderDto getById(@PathVariable long id){
        ShopOrder shop = shopOrderService.getById(id);
        return new ShopOrderDto(shop);
    }

    @PostMapping()
    public ResponseEntity<ShopOrder> createShopOrder(@RequestBody ShopOrderRequestDto shopOrderRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOrderService.insertNewShopOrder(shopOrderRequestDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ShopOrder> updateShopOrder(@PathVariable long id, @RequestBody Map<String, Status> changes){
        return ResponseEntity.ok(shopOrderService.updatePartial(id, changes));

    }

  /*
  @GetMapping("/teste")
    public ResponseEntity<List<BatchStock>> teste() {
        return ResponseEntity.ok(testerepo.findAllBySellerAdSellerAdId(1L));
    }*/
}