package com.meli.desafio_final.service;

import com.meli.desafio_final.model.PromoCode;
import com.meli.desafio_final.repository.IPromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoCodeService {

    @Autowired
    IPromoCodeRepository promoCodeRepository;

    public PromoCode registerNewPromo(PromoCode newPromoCode){
        return promoCodeRepository.save(newPromoCode);
    }
}
