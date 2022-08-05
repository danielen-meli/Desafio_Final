package com.meli.desafio_final.service;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.repository.ShopOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShopOrderService {

    @Autowired
    private ShopOrderRepo shopOrderRepo;

    //@Transactional
    public ShopOrder save(ShopOrder shopOrder){
        shopOrder.getShopOrderItem();
        return shopOrderRepo.save(shopOrder);

    }

}
