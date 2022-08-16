package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BuyerResponseDto;
import com.meli.desafio_final.dto.SellerHistoryByProductsResponseDto;
import com.meli.desafio_final.dto.SellerHistoryDto;
import com.meli.desafio_final.dto.SellerHistoryTotalSoldResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.ISellerHistoryByProduct;
import com.meli.desafio_final.model.ISellerHistoryTotalSold;
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

    public List<BuyerResponseDto> getBuyersOrderedBuyPurchaseQuantity(String orderBy) {
        if(orderBy.equalsIgnoreCase("asc")) {
            List<Buyer> buyers = buyerRepository.findByOrderByQuantityPurchasedAsc();
            return buyers.stream().map(BuyerResponseDto::new).collect(Collectors.toList());
        } else if(orderBy.equalsIgnoreCase("desc")) {
            List<Buyer> buyers = buyerRepository.findByOrderByQuantityPurchasedDesc();
            return buyers.stream().map(BuyerResponseDto::new).collect(Collectors.toList());
        }
        throw new BadRequestException("Invalid orderBy type");
    }

    public List<BuyerResponseDto> getBuyersBetweenValuesPurchaseOrderedBuyPurchaseQuantity(double purchaseStart, double purchaseEnd, String orderBy) {
        if(orderBy.equalsIgnoreCase("asc")) {
            List<Buyer> buyers = buyerRepository.findBuyerBetweenPurchaseValueAsc(purchaseStart, purchaseEnd);
            return buyers.stream().map(BuyerResponseDto::new).collect(Collectors.toList());
        } else if(orderBy.equalsIgnoreCase("desc")) {
            List<Buyer> buyers = buyerRepository.findBuyerBetweenPurchaseValueDesc(purchaseStart, purchaseEnd);
            return buyers.stream().map(BuyerResponseDto::new).collect(Collectors.toList());
        }
        throw new BadRequestException("Invalid orderBy type");
    }


    // Sellers part

    public List<SellerHistoryDto> getAllSellersHistory() {
        return sellerHistoryRepository.findAll().stream().map(SellerHistoryDto::new).collect(Collectors.toList());
    }

    public List<SellerHistoryTotalSoldResponseDto> getSellersOrderedByTotalSold() {
        List<ISellerHistoryTotalSold> totalSellerSold = sellerHistoryRepository.sellerHistoryTotalSold();
        return totalSellerSold.stream().map(SellerHistoryTotalSoldResponseDto::new).collect(Collectors.toList());
    }

    public List<SellerHistoryByProductsResponseDto> getSellersHistoryByProduct(long productId) {
        List<ISellerHistoryByProduct> sellerHistoryByProducts = sellerHistoryRepository.getSellersHistoryByProduct(productId);
        return sellerHistoryByProducts.stream().map(SellerHistoryByProductsResponseDto::new).collect(Collectors.toList());
    }

}
