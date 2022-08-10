package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.model.ShopOrder;

public interface IShopOrderService {
        ShopOrderResponseDto insertNewShopOrder(ShopOrderRequestDto shopOrderRequestDto);
        ShopOrder closedShopOrder(long id);
        ShopOrder getById(Long id);
}

