package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerHistoryByProductsResponseDto;
import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.dto.SellerHistoryTotalSoldResponseDto;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.SellerHistory;

import java.util.List;

public interface ISellerBuyerHistoryService {
    Buyer getABuyer(long id);
    List<Buyer> getBuyersOrderedBuyPurchaseQuantity(String orderBy);
    List<SellerHistoryDto> getAllSellersHistory();
    List<Buyer> getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(double purchaseStart, double purchaseEnd, String orderBy);
    List<SellerHistoryTotalSoldResponseDto> getSellersOrderedByTotalSold();
    List<SellerHistoryByProductsResponseDto> getSellersHistoryByProduct(long productId);
}
