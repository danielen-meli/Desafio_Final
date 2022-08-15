package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ISectionRepository extends JpaRepository<Section, Long> {

    @Query(nativeQuery = true, value = "SELECT sec.section_id from section as sec where sec.category = category")
    List<BigInteger> getSectionsIdsByCategory(@Param("category") Category category);

}
