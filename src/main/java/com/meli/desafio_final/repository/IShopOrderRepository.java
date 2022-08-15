package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.ShopOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShopOrderRepository extends JpaRepository<ShopOrder, Long> {

}
