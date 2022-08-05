package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.IInboundOrderRepository;
import com.meli.desafio_final.repository.ISectionRepository;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundService {
    // TODO: somar o currentquantity de todos ods batchstock para saber se é menor q a section.sectioncapacity
    // TODO: verificar pela temperatura se corresponde aquela seção

    @Autowired
    private IInboundOrderRepository inboundOrderRepo;

    @Autowired
    private IWarehouseRepository warehouseRepo;

    @Autowired
    private ISectionRepository iSectionRepo;

    @Autowired
    private ISellerAdRepository iSellerAdRepo;


//    QUANDO o representante entra no setor
//    ENTÃO o registro de compra é criado
//    E o lote é atribuído a um setor
//    E o representante é associado ao registro de estoque

    // valida se o warehouse é valido
    private boolean isWarehouseValid(long id){
        if(warehouseRepo.findById(id).isPresent()){
            return true;
        }
        // TODO: caso o warehouse seja invalido retornar uma exceção
        return false;
    }

    private boolean isSectionValid(Section section){
        if(section != null) {
            return true;
        }
        // TODO: caso o section seja invalida retornar uma exceção
        return false;
    }

    private boolean isSectionCapacityValid(List<BatchStock> batchStockList, double sectionCapacity){
        double sumBatchStocksVolume = batchStockList.stream().mapToDouble(BatchStock::getVolume).sum();

        if(sumBatchStocksVolume <= sectionCapacity) {
            return true;
        }
        // TODO: lançar uma exceção
        return false;
    }

    private boolean isAlltypeProductsValid(List<BatchStockRequestDto> batchStockList, Category sectionCategory){
        batchStockList.forEach(bs -> {
            SellerAd sellerAd = iSellerAdRepo.findById(bs.getSellerAdId()).get();
            if(!sellerAd.getProduct().getCategory().equals(sectionCategory)){
                // TODO: lançar exception de categoria diferente

            }
        });
        return true;
    }



}
