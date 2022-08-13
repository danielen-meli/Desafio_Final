package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.SellerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISellerHistoryRepository extends JpaRepository<SellerHistory, Long> {
}
