package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.SellerHistory;
import com.meli.desafio_final.repository.IBuyerRepository;
import com.meli.desafio_final.repository.ISellerHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerBuyerHistoryService implements ISellerBuyerHistoryService {

    @Autowired
    private IBuyerRepository buyerRepository;

    @Autowired
    private ISellerHistoryRepository sellerHistoryRepository;


    public Buyer getABuyer(long id) {
        return buyerRepository.findById(id).orElseThrow(() -> new BadRequestException("Buyer does't exists"));
    }

    public List<Buyer> getBuyersOrderedBuyPurchaseQuantity(String orderBy) {
        if(orderBy.equalsIgnoreCase("asc")) {
            return buyerRepository.findByOrderByQuantityPurchasedAsc();
        } else if(orderBy.equalsIgnoreCase("desc")) {
            return buyerRepository.findByOrderByQuantityPurchasedDesc();
        }
        throw new BadRequestException("Invalid orderBy type");
    }

    public List<SellerHistoryDto> getAllSellersHistory() {
        return sellerHistoryRepository.findAll().stream().map(SellerHistoryDto::new).collect(Collectors.toList());
    }



}
