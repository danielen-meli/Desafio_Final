package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.SellerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISellerHistoryRepository extends JpaRepository<SellerHistory, Long> {
    @Query(nativeQuery = true, value = "SELECT * from seller_history as sh where sh.seller_seller_id = :sellerId and sh.seller_ad_seller_ad_id = :sellerAdId")
    SellerHistory getSellerHistoryIfExists(@Param("sellerId") Long sellerId, @Param("sellerAdId") Long sellerAdId);
}
