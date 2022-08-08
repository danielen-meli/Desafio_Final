package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.ShopOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderItemRepo extends JpaRepository<ShopOrderItem, Long> {
}
