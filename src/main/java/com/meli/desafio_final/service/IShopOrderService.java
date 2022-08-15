package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.DiscountResponseDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.model.ShopOrder;

import java.util.List;

public interface IShopOrderService {
        ShopOrderResponseDto insertNewShopOrder(ShopOrderRequestDto shopOrderRequestDto);
        ShopOrder closedShopOrder(long id);
        ShopOrder getById(Long id);
        List<DiscountResponseDto> discountsAvailable(long shopOrder);
}

