package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.SellerAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ISellerAdRepository extends JpaRepository<SellerAd, Long> {
}
