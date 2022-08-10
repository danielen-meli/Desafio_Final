package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBatchStockRepository extends JpaRepository<BatchStock, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(bs.current_quantity) from BATCH_STOCK bs group by bs.seller_ad_seller_ad_id = :sellerId")
    long getQuantityProduct(@Param("sellerId") Long sellerId);
}
