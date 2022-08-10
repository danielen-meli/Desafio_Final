package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.ShopOrderDto;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;

import java.util.Optional;

public interface IShopOrderService {
        ShopOrder save(ShopOrderDto dto);
        Optional<ShopOrder> shopOrderCompleted(long id);
        void setStatus(Long id, Status status);
}

