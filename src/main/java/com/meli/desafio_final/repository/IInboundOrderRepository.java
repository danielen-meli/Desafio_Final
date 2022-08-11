package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface IInboundOrderRepository extends JpaRepository<InboundOrder, Long> {
    List<InboundOrder> findAllBySectionSectionId(long sectionId);

    @Query(nativeQuery = true, value= "SELECT * FROM inbound_order io WHERE io.section_id in :ids")
    List<InboundOrder> getInboundOrder(List<BigInteger> ids);
}
