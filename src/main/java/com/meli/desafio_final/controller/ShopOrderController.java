package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.ShopOrderDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.service.IShopOrderService;
import com.meli.desafio_final.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class ShopOrderController{

    @Autowired
    private ShopOrderService shopOrderService;
    
    /**
     * Lists purchase orders by cart ID.
     * @param id ad shop identification number
     * @return the purchase order for the requested ID.
     */
    @GetMapping("/{id}")
    private ResponseEntity<ShopOrderDto> getById(@PathVariable long id){
        ShopOrder shop = shopOrderService.getById(id);
        return ResponseEntity.ok(new ShopOrderDto(shop));
    }

    /**
     * Generates a possible shopping cart.
     * @param shopOrderRequestDto shop order number
     * @return A valid shopping cart and a positive response to the user.
     */
    @PostMapping()
    public ResponseEntity<ShopOrderResponseDto>
        createShopOrder(@RequestBody ShopOrderRequestDto shopOrderRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOrderService.insertNewShopOrder(shopOrderRequestDto));
    }

    /**
     * A valid shopping cart is closed
     * @param id order shop identification number
     * @return a completed purchase order
     */
    @PutMapping("/closed-shopOrder/{id}")
    public ResponseEntity<ShopOrder> closeShopOrder(@PathVariable long id){
        return ResponseEntity.ok(shopOrderService.closedShopOrder(id));
    }
}
