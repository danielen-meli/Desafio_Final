package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.ShopOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface IShopOrderItemRepository extends JpaRepository<ShopOrderItem, Long> {
    @Query(nativeQuery = true, value = "select * from shop_order_item as soi where soi.shop_order_id = :shopOrderId")
    List<ShopOrderItem> findAllByShopOrderId(BigInteger shopOrderId);
}

