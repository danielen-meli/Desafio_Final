package com.meli.desafio_final.service;

import com.meli.desafio_final.model.BatchStock;

import java.util.List;

public interface IBatchStockService {
    List<BatchStock> getProductsInStock();
    List<BatchStock> getProductsStockOrdered();
}
