package com.meli.desafio_final.service;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.ShopOrderItem;
import com.meli.desafio_final.repository.ShopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderService {

    @Autowired
    private ShopOrderRepository repository;

    public ShopOrder getById(long id){
        return repository.findById(id).get();
    }
}
