package com.meli.desafio_final.service;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IShopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class ShopOrderService {

    @Autowired
    private IShopOrderRepository shopOrderRepo;

    @Transactional // save e importante ter rollback en caso de erro
    public ShopOrder save(@Valid ShopOrder shopOrder){
        //shopOrder.getShopOrderItem()
        return shopOrderRepo.save(shopOrder);
    // aqui recebe a lista, mas tem que verificar os itens todos, para validar o estoque.
        // qdo mudar o status pra close é que decrementa a quantidade dos itens

    }

    public ShopOrder getById(long id){
        return shopOrderRepo.findById(id).get();
    }

    public ShopOrder updatePartial(long id, Map<String, Status> changes) {
        ShopOrder shopOrder = getById(id);

        changes.forEach( (attribute, value)-> {
            switch (attribute){
                case "status": shopOrder.setStatus(value); break;
            }
        });

        return shopOrderRepo.save(shopOrder);
    }
}
