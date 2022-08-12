package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.OrderAdRequestDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.*;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ShopOrderService implements IShopOrderService {

    @Autowired
    private IShopOrderRepository shopOrderRepository;

    @Autowired
    private IBuyerRepository buyerRepository;

    @Autowired
    private IBatchStockRepository batchStockRepository;

    @Autowired
    private ISellerAdRepository sellerAdRepository;

    @Autowired
    private ISectionRepository sectionRepository;

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

            long weeks = ChronoUnit.WEEKS.between(LocalDate.now(), bs.getDueDate());
            if (weeks < 3){
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

    private ShopOrderResponseDto sumShopOrderItem(List<ShopOrderItem> shopOrderItemList){
        double total = shopOrderItemList.stream().mapToDouble(so-> so.getQuantity() * so.getPrice()).sum();

        return ShopOrderResponseDto.builder()
                .totalPrice(total)
                .build();
    }

    @Override
    @Transactional // save e importante ter rollback en caso de erro
    public ShopOrderResponseDto insertNewShopOrder(ShopOrderRequestDto shopOrderRequestDto) {
        Buyer buyer = verifyBuyerExists(shopOrderRequestDto.getBuyerId());
        productStocksAvailable(shopOrderRequestDto.getProducts());

        ShopOrder shopOrderSaved = save(shopOrderRequestDto, buyer);
        List<ShopOrderItem> shopOrderItems = shopOrderSaved.getShopOrderItem();
        return sumShopOrderItem(shopOrderItems);
    }

    @Override
    public ShopOrder getById(Long id){
        Optional<ShopOrder> shopOrder = shopOrderRepository.findById(id);
        if(shopOrder.isEmpty())
            throw new NotFoundException("Shop order not found");

        return shopOrder.get();
    }

    private void updateSectionCapacity(BatchStock batchStock) {
        Section sectionToUpdateCapacity = batchStock.getInboundOrder().getSection();
        sectionToUpdateCapacity.setSectionCapacity(sectionToUpdateCapacity.getSectionCapacity() + batchStock.getVolume());
        sectionRepository.save(sectionToUpdateCapacity);
    }

    @Override
    public ShopOrder closedShopOrder(long id){
        ShopOrder shopOrder = getById(id);

        if (shopOrder.getStatus().equals(Status.CLOSED))
            throw new IllegalArgumentException("carrinho ja esta fechado");

        shopOrder.getShopOrderItem().forEach(item -> {

            if (item.getQuantity() > batchStockRepository.getQuantityProduct(item.getSellerAd().getSellerAdId())){
                String productName = item.getSellerAd().getProduct().getProductName();
                throw new IllegalArgumentException("Estoque nao possui quantidade suficiente de " + productName);
            }
        });

        shopOrder.setStatus(Status.CLOSED);

        shopOrder.getShopOrderItem().forEach(item -> {

            int quantityToBuy = item.getQuantity();

            List<BatchStock> batchStockList = item.getSellerAd().getBatchStockId();
            for (BatchStock batchStock: batchStockList) {
                int currentQuantity = batchStock.getCurrentQuantity();

                if (currentQuantity > quantityToBuy){
                    currentQuantity -= quantityToBuy;
                    batchStock.setCurrentQuantity(currentQuantity);
                    batchStockRepository.save(batchStock);
                    break;
                } else{
                    quantityToBuy -= currentQuantity;
                    currentQuantity = 0;
                    batchStock.setCurrentQuantity(currentQuantity);
                    // função q atualiza a capacidade da seção quando comprar o total
                    batchStockRepository.save(batchStock);
                    updateSectionCapacity(batchStock);
                }
            }
        });
        return shopOrderRepository.save(shopOrder);
    }


}