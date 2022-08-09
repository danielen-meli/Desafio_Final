package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuyerRepository extends JpaRepository<Buyer, Long> {
}
