package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBatchStockRepository extends JpaRepository<BatchStock, Long> {
    List<BatchStock> findAllBySellerAdSellerAdId (long SellerAdId);

    @Query(nativeQuery = true, value = "SELECT SUM(bs.current_quantity) from BATCH_STOCK bs where bs.seller_ad_seller_ad_id = :sellerId")
    long getQuantityProduct(@Param("sellerId") Long sellerId);
}
