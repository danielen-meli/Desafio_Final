package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query(nativeQuery = true, value = "select sum(current_quantity) from section s\n" +
            "\tjoin inbound_order ino on s.section_id = ino.section_id\n" +
            "    join batch_stock bs on ino.inbound_order_id = bs.inbound_order_id\n" +
            "    join seller_ad sa on bs.seller_ad_seller_ad_id = sa.seller_ad_id\n" +
            "    where warehouse_warehouse_id = :wareHouseId\n" +
            "    and bs.seller_ad_seller_ad_id = :sellerAdId\n" +
            "    group by bs.seller_ad_seller_ad_id;\n")
    long getAllProducts(@Param("sellerAdId") Long sellerAdId, @Param("wareHouseId") Long wareHouseId);
}
