package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromoCodeRepository extends JpaRepository<PromoCode, String> {
}
