package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISectionRepository extends JpaRepository<Section, Long> {
}
