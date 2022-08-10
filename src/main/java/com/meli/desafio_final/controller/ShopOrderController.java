package com.meli.desafio_final.controller;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.service.ShopOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meli.desafio_final.dto.ShopOrderDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import static com.meli.desafio_final.model.enums.Status.CLOSED;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class ShopOrderController{
    @Autowired
    private ShopOrderService shopOrderService;

    @GetMapping("/{id}")
    private ShopOrderDto getById(@PathVariable long id){
        ShopOrder shop = shopOrderService.getById(id);
        return new ShopOrderDto(shop);
    }

    @PostMapping("/api/v1/fresh-products/orders/")
    public ResponseEntity<Object> createShopOrder(@RequestBody @Valid ShopOrderDto shopOrderDto){
        var ShopOrderModel = new ShopOrder();
        BeanUtils.copyProperties(shopOrderDto, ShopOrderModel);
        // apesar do usuario add no carrinho só com produco/ preço, ele tem que entrar tudo q tem no model pra pegar das tabelas relacionadas
        ShopOrderModel.setStatus(CLOSED); // fecha o carrinho pra poder criar a compra e retornar o created.
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOrderService.save(ShopOrderModel));
    }

    @PutMapping("/closed-shopOrder/{id}")
    public ResponseEntity<ShopOrder> closeShopOrder(@PathVariable long id){
        return ResponseEntity.ok(shopOrderService.closedShopOrder(id));

    }
}