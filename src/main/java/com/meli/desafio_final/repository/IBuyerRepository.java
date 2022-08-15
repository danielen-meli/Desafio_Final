package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBuyerRepository extends JpaRepository<Buyer, Long> {
    List<Buyer> findByOrderByQuantityPurchasedAsc();

    List<Buyer> findByOrderByQuantityPurchasedDesc();

    @Query(nativeQuery = true, value = "SELECT * from buyer b where b.quantity_purchased between :start and :end order by b.quantity_purchased asc")
    List<Buyer> findBuyerBetweenPurchaseValueAsc(@Param("start") double start, @Param("end") double end);


    @Query(nativeQuery = true, value = "SELECT * from buyer as b where b.quantity_purchased between :start and :end order by b.quantity_purchased desc")
    List<Buyer> findBuyerBetweenPurchaseValueDesc(@Param("start") double start, @Param("end") double end);
}
