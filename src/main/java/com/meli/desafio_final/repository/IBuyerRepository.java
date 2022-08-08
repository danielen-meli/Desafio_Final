package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuyerRepository extends JpaRepository<Buyer, Long> {
}
