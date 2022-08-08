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
        return ShopOrderDto.converter(shop);
    }

    @PostMapping("/api/v1/fresh-products/orders/")
    public ResponseEntity<Object> createShopOrder(@RequestBody @Valid ShopOrderDto shopOrderDto){
        var ShopOrderModel = new ShopOrder();
        BeanUtils.copyProperties(shopOrderDto, ShopOrderModel);
        // apesar do usuario add no carrinho só com produco/ preço, ele tem que entrar tudo q tem no model pra pegar das tabelas relacionadas
        ShopOrderModel.setStatus(CLOSED); // fecha o carrinho pra poder criar a compra e retornar o created.
        return ResponseEntity.status(HttpStatus.CREATED).body(shopOrderService.save(ShopOrderModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopOrder> updateShopOrder(@PathVariable long id, @RequestBody Map<String, Status> changes){
        return ResponseEntity.ok(shopOrderService.updatePartial(id, changes));

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
