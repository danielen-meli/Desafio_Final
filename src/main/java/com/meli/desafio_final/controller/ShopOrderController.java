package com.meli.desafio_final.controller;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.ShopOrderItem;
import com.meli.desafio_final.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/fresh-products/orders")
public class ShopOrderController {
    @Autowired
    private ShopOrderService shopOrderService;


    @PostMapping()
    public ResponseEntity<ShopOrder> createShopOrder(@RequestBody ShopOrder shopOrder){
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOrderService.save(shopOrder));
    }

}
  /**  Registre um pedido com a lista de produtos que compõem o
        PurchaseOrder. Calcule o preço final
        e devolva-o juntamente com o código
        de status "201 CREATED".
        Se não houver estoque de um
        produto, notifique a situação
        retornando um erro por produto, não
        no nível do pedido **/