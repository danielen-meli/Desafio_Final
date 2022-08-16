package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.ISellerHistoryByProduct;
import com.meli.desafio_final.model.ISellerHistoryTotalSold;
import com.meli.desafio_final.model.SellerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISellerHistoryRepository extends JpaRepository<SellerHistory, Long> {
    @Query(nativeQuery = true, value = "SELECT * from seller_history as sh where sh.seller_seller_id = :sellerId and sh.seller_ad_seller_ad_id = :sellerAdId")
    SellerHistory getSellerHistoryIfExists(@Param("sellerId") Long sellerId, @Param("sellerAdId") Long sellerAdId);

    @Query(nativeQuery = true, value = "SELECT SUM(sh.quantity) as total_sold, sl.seller_id as seller_id, sl.seller_name as seller_name, us.email as seller_email FROM meli_fresh.seller_history as sh\n" +
            "INNER JOIN meli_fresh.seller as sl\n" +
            "on sh.seller_seller_id = sl.seller_id\n" +
            "INNER JOIN meli_fresh.seller_ad as sa\n" +
            "on sh.seller_ad_seller_ad_id = sa.seller_ad_id\n" +
            "INNER JOIN meli_fresh.user as us\n" +
            "ON sl.user_user_id = us.user_id\n" +
            "GROUP BY sh.seller_seller_id order by total_sold DESC;")
    List<ISellerHistoryTotalSold> sellerHistoryTotalSold();

    @Query(nativeQuery = true, value = "SELECT sh.id, sh.quantity as total_quantity_sold, sh.seller_seller_id, sl.seller_name, us.email, sa.price, pd.product_name FROM meli_fresh.seller_history as sh\n" +
            "INNER JOIN meli_fresh.seller_ad as sa\n" +
            "ON sh.seller_ad_seller_ad_id = sa.seller_ad_id\n" +
            "INNER JOIN meli_fresh.seller as sl\n" +
            "ON sh.seller_seller_id = sl.seller_id\n" +
            "INNER JOIN meli_fresh.product as pd\n" +
            "ON pd.product_id = :productId\n" +
            "INNER JOIN meli_fresh.user as us\n" +
            "ON us.user_id = sl.user_user_id\n" +
            "WHERE sa.product_product_id = :productId\n" +
            "ORDER BY sh.quantity DESC;")
    List<ISellerHistoryByProduct> getSellersHistoryByProduct(@Param("productId") Long productId);
}
