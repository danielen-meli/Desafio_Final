package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.InboundOrderResponseDto;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InboundService implements IInboundService {
    // TODO: somar o currentquantity de todos ods batchstock para saber se é menor q a section.sectioncapacity
    // TODO: verificar pela temperatura se corresponde aquela seção

    @Autowired
    private IInboundOrderRepository inboundOrderRepo;

    @Autowired
    private IWarehouseRepository warehouseRepo;

    @Autowired
    private ISectionRepository sectionRepo;

    @Autowired
    private ISellerAdRepo sellerAdRepo;

    @Autowired
    private IBatchStockRepository batchStockRepo;


//    QUANDO o representante entra no setor
//    ENTÃO o registro de compra é criado
//    E o lote é atribuído a um setor
//    E o representante é associado ao registro de estoque

    // valida se o warehouse é valido
    private void isWarehouseValid(long id){
        if(warehouseRepo.findById(id).isEmpty()){
            // TODO: caso o warehouse seja invalido retornar uma exceção
        }
    }

    private void isSectionValid(Section section){
        if(section == null) {
            // TODO: throw exception
        }
    }

    private void isSectionCapacityValid(List<BatchStockRequestDto> batchStockList, double sectionCapacity){
        double sumBatchStocksVolume = batchStockList.stream().mapToDouble(BatchStockRequestDto::getVolume).sum();

        if(sumBatchStocksVolume <= sectionCapacity) {
            // TODO: lançar uma exceção
        }
    }

    private void isAlltypeProductsValid(List<BatchStockRequestDto> batchStockList, Category sectionCategory){
        batchStockList.forEach(bs -> {
            SellerAd sellerAd = sellerAdRepo.findById(bs.getSellerAdId()).get();
            if(!sellerAd.getProduct().getCategory().equals(sectionCategory)){
                // TODO: lançar exception de categoria diferente

            }
        });
    }

    // atualiza ou insere um inbound order
    private List<BatchStock> saveInboundOrder(InboundOrderRequestDto inboundOrder) {
        Section section = sectionRepo.findById(inboundOrder.getSection()).get();
        isSectionValid(section);

        long warehouseId = section.getWarehouse().getWarehouseId();
        isWarehouseValid(warehouseId);

        isSectionCapacityValid(inboundOrder.getBatchStockList(), section.getSectionCapacity());

        isAlltypeProductsValid(inboundOrder.getBatchStockList(), section.getCategory());

        InboundOrder inboudOrder = InboundOrder.builder()
                .orderDate(LocalDate.now())
                .section(section)
                .build();

        InboundOrder inboundOrderSaved = inboundOrderRepo.save(inboudOrder);

        List<BatchStock> newBatchStockList = inboundOrder.getBatchStockList().stream().map(batchStockRequest -> {
           SellerAd sellerAdFound = sellerAdRepo.findById(batchStockRequest.getSellerAdId()).get();
           return new BatchStock(batchStockRequest, sellerAdFound, inboundOrderSaved);
        }).collect(Collectors.toList());

        List<BatchStock> batchStocksSaved = new ArrayList<BatchStock>();

        newBatchStockList.forEach(batchStock -> {
            batchStocksSaved.add(batchStockRepo.save(batchStock));
        });

        return batchStocksSaved;
    }

    @Override
    public List<BatchStock> insertNewInboundOrder(InboundOrderRequestDto newInboundOrder) {
        if(newInboundOrder.getId() != 0){
            //  TODO: lançar exception de InboundOrder já cadastrada
        }
        return saveInboundOrder(newInboundOrder);

    }

    @Override
    public List<BatchStock> updateNewInboundOrder(InboundOrderRequestDto newInboundOrder) {
        Optional<InboundOrder> inboundFound = inboundOrderRepo.findById(newInboundOrder.getId());
        if(inboundFound.isPresent()){
            return saveInboundOrder(newInboundOrder);
        }
        //  TODO: lançar exception de InboundOrder já cadastrada
        return null;
    }
}
