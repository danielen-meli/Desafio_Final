package com.meli.desafio_final.service;

import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.exception.QuantityException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.repository.IBuyerRepository;
import com.meli.desafio_final.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
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
    public ShopOrder save(ShopOrder shopOrder) {
        // verificar se o comprador está cadastrado:
        Optional<Buyer> buyerExist = buyerRepository.findById(shopOrder.getBuyer().getBuyerId());
        if (buyerExist.isEmpty()) throw new NotFoundException("Comprador não cadastrado!");

        shopOrder.getShopOrderItem().forEach(product -> {
            BatchStock productInfo = (BatchStock) batchStockRepository.findById(product.getId()).orElse(null);
            // verificar a quantidade de cada produto da lista
            assert productInfo != null;
            if (productInfo.getCurrentQuantity() < product.getQuantity()) {
                throw new QuantityException("Não há quantidade suficiente no estoque!");
            }
            // validacao da data de validade - poderia ser o period
            long expirationDate = ChronoUnit.DAYS.between(productInfo.getDueDate(), now());
            if (expirationDate < 21) {
                throw new QuantityException("Validade é menor que 3 semanas!");
            }
        });
        return shopOrderRepository.save(shopOrder);
    } // depois q valida, o carrinho pode ser fechado - fazer isso num put pra mudar o status e ai atualiza o estoque


    public ShopOrder getById(long id){
        return shopOrderRepository.findById(id).get();
    }

    public ShopOrder updatePartial(long id, Map<String, Status> changes) {
        ShopOrder shopOrder = getById(id);

        changes.forEach( (attribute, value)-> {
            switch (attribute){
                case "status": shopOrder.setStatus(value); break;
            }
        });

        return shopOrderRepository.save(shopOrder);
    }
}
