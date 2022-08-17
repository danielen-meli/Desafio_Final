package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IShopOrderRepository extends JpaRepository<ShopOrder, Long> {

    Optional<ShopOrder> findByBuyerBuyerId(Long id);
}
