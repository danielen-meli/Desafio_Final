package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.ShopOrderDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;

import java.util.Optional;

public interface IShopOrderService {
        ShopOrderResponseDto insertNewShopOrder(ShopOrderRequestDto shopOrderRequestDto);
        ShopOrder closedShopOrder(long id);
        ShopOrder getById(Long id);
}

