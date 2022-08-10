package com.meli.desafio_final.service;

import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchStockService implements IBatchStockService{

    @Autowired
    IBatchStockRepository batchStockRepository;

    @Override
    public List<BatchStock> getProductsInStock() {
        if(batchStockRepository.findAll().isEmpty()){
            throw new NotFoundException("Não existem produtos em estoque.");
        }
        return batchStockRepository.findAll();
    }

    @Override
    public List<BatchStock> getProductsStockOrdered() {
        return null;
    }
}
