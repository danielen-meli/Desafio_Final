package com.meli.desafio_final.service;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.repository.ShopOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.desafio_final.model.ShopOrderItem;
import com.meli.desafio_final.repository.ShopOrderRepository;
import java.util.List;
import javax.transaction.Transactional;

@Service
public class ShopOrderService {

    @Autowired
    private ShopOrderRepo shopOrderRepo;

    @Transactional // save e importante ter rollback en caso de erro
    public ShopOrder save(ShopOrder shopOrderModel){
        // shopOrder.getShopOrderItem()
        return shopOrderRepo.save(shopOrderModel);
    // aqui recebe a lista, mas tem que verificar os itens todos, para validar o estoque.
        // qdo mudar o status pra close é que decrementa a quantidade dos itens

    }
    
    public ShopOrder getById(long id){
        return shopOrderRepo.findById(id).get();
    }
}
