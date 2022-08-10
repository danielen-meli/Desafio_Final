package com.meli.desafio_final.service;

import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.Warehouse;

import java.util.List;

public interface IWarehouseService {
    long getTotalQuantitySellerAdinWareHouse(SellerAd sellerAdId, Warehouse wareHouseId);
}
