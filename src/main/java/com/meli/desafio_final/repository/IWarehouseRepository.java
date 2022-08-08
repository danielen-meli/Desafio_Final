package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseRepository extends CrudRepository<Warehouse, Long> {
}
