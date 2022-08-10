package com.meli.desafio_final.service;

import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.exception.QuantityException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.repository.IBuyerRepository;
import com.meli.desafio_final.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

import static java.time.LocalTime.now;

@Service
public class ShopOrderService {

    @Autowired
    private IShopOrderRepository shopOrderRepository;

    @Autowired
    private IBuyerRepository buyerRepository;

    @Autowired
    private IBatchStockRepository batchStockRepository;


    @Transactional // save e importante ter rollback en caso de erro
    public ShopOrder save(@Valid ShopOrder shopOrder) {
        // verificar se o comprador está cadastrado:
        boolean buyerExist = buyerRepository.exists((Example) shopOrder.getBuyer());
        if (!buyerExist) throw new NotFoundException("Comprador não cadastrado!");

        // TODO: 08/08/22 : ver com Mauri pq está dando essas marcações!
        shopOrder.getShopOrderItem().stream().forEach(product -> {
            BatchStock productInfo = (BatchStock) batchStockRepository.findById(product.getId()).orElse(null);
            // verificar a quantidade de cada produto da lista
            if (productInfo.getCurrentQuantity() < product.getQuantity()) {
                throw new QuantityException("Não há quantidade suficiente no estoque!");
            }
            // validacao da data de validade
            long expirationDate = ChronoUnit.DAYS.between(productInfo.getDueDate(), now());
            if (expirationDate < 21) {
                throw new QuantityException("Validade é menor que 3 semanas!");
            }
            ;
        });
        return shopOrderRepository.save(shopOrder);
    }


    public ShopOrder getById(Long id){
        Optional<ShopOrder> shopOrder = shopOrderRepository.findById(id);
        if(shopOrder.isEmpty())
            throw new NotFoundException("Shop order not found");

        return shopOrder.get();
    }


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

                if (currentQuantity >= quantityToBuy){
                    currentQuantity -= quantityToBuy;
                    batchStock.setCurrentQuantity(currentQuantity);
                    batchStockRepository.save(batchStock);
                    break;
                }else{
                    quantityToBuy -=currentQuantity;
                    currentQuantity = 0;
                    batchStock.setCurrentQuantity(currentQuantity);
                    batchStockRepository.save(batchStock);
                }
            }
        });
        return shopOrderRepository.save(shopOrder);
    }
}
