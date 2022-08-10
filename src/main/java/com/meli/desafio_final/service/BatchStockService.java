package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchStockService implements IBatchStockService{

    @Autowired
    IBatchStockRepository batchStockRepository;

    @Override
    public List<BatchStockDto> getProductsInStock() {
        List<BatchStockDto> listBatchDto = batchStockRepository.findAll().stream().
                map(BatchStockDto::new).collect(Collectors.toList());

        if(listBatchDto.isEmpty()){
            throw new NotFoundException("Não existem produtos em estoque.");
        }

        return listBatchDto;
    }

    @Override
    public List<BatchStockDto> getProductsStockOrdered() {
        return null;
    }
}
