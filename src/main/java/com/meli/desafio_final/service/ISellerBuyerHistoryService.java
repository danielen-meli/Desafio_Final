package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.SellerHistory;

import java.util.List;

public interface ISellerBuyerHistoryService {
    Buyer getABuyer(long id);
    List<Buyer> getBuyersOrderedBuyPurchaseQuantity(String orderBy);
    List<SellerHistoryDto> getAllSellersHistory();
}
