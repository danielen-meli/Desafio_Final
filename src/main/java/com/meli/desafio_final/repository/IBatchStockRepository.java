package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IBatchStockRepository extends JpaRepository<BatchStock, Long> {
}
