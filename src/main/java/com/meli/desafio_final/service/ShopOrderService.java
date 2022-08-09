package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.OrderAdRequestDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.model.*;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.repository.IBuyerRepository;
import com.meli.desafio_final.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;


import static java.time.LocalTime.now;

@Service
public class ShopOrderService {

    @Autowired
    private IShopOrderRepository shopOrderRepository;

    @Autowired
    private IBuyerRepository buyerRepository;

    @Autowired
    private IBatchStockRepository batchStockRepository;

    @Autowired
    private ISellerAdRepository sellerAdRepository;

    // verifica o buyer
    private Buyer verifyBuyerExists(long id){
        return buyerRepository.findById(id).orElseThrow(() -> new BadRequestException("Buyer does't exists"));
    }

    private void productStocksAvailable(List<OrderAdRequestDto> products){
        products.forEach(orderRequest -> {
            long sellerAdId = orderRequest.getSellerAdId();
            int orderQuantity = orderRequest.getQuantity();
            List<BatchStock> batchStocksFound = batchStockRepository.findAllBySellerAdSellerAdId(sellerAdId);

            List<BatchStock> batchStocksWithDueDateAvailable = filterBatchStocksByDueDate(batchStocksFound);
            isStockQuantityAvailable(batchStocksWithDueDateAvailable, orderQuantity);

        });
    }

    // valida que a quantidade em stock é suficiente para a request
    private void isStockQuantityAvailable(List<BatchStock> batchStockList, int orderQuantity){
        double stockQuantityAvailable = batchStockList.stream().mapToDouble(BatchStock::getCurrentQuantity).sum();
        if(orderQuantity > stockQuantityAvailable) {
            throw new BadRequestException("Order quantity requested is bigger than available stock");
        }
    }

    // Retorna apenas os BatchStocks que o due date é maior q 21
    private List<BatchStock> filterBatchStocksByDueDate(List<BatchStock> batchStockList){
         List<BatchStock> batchStockListValid = batchStockList.stream().filter(bs-> {
            Period expirationDate = Period.between(bs.getDueDate(), LocalDate.now());
            if (expirationDate.getDays() < 21){
                return false;
            }
            return true;
         }).collect(Collectors.toList());

         if(batchStockListValid.isEmpty()){
             throw new BadRequestException("There are no products available now. Sorry");
         }
         return batchStockListValid;
    }

    private ShopOrder save(ShopOrderRequestDto shopOrderRequestDto, Buyer buyer) {
        List<ShopOrderItem> shopOrderItems = shopOrderRequestDto.getProducts().stream().map(order -> {
            SellerAd sellerAd = sellerAdRepository.findById(order.getSellerAdId()).orElseThrow(() -> new BadRequestException("SellerAd Invalid"));
            int quantity = order.getQuantity();
            LocalDate date = shopOrderRequestDto.getDate();
            return new ShopOrderItem(date, quantity, sellerAd);
        }).collect(Collectors.toList());

        ShopOrder shopOrder = new ShopOrder(shopOrderRequestDto, buyer, shopOrderItems);
        return shopOrderRepository.save(shopOrder);
    }

    @Transactional // save e importante ter rollback en caso de erro
    public ShopOrder insertNewShopOrder(ShopOrderRequestDto shopOrderRequestDto) {
        Buyer buyer = verifyBuyerExists(shopOrderRequestDto.getBuyerId());
        productStocksAvailable(shopOrderRequestDto.getProducts());

        return save(shopOrderRequestDto, buyer);
    }

    public ShopOrder getById(long id) {
        return shopOrderRepository.findById(id).get();
    }

    public ShopOrder updatePartial(long id, Map<String, Status> changes) {
        ShopOrder shopOrder = getById(id);

        changes.forEach((attribute, value) -> {
            switch (attribute) {
                case "status":
                    shopOrder.setStatus(value);
                    break;
            }
        });
        return shopOrderRepository.save(shopOrder);
    }

}