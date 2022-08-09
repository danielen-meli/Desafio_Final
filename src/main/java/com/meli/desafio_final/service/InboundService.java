package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.InboundOrderResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
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
    private ISellerAdRepository sellerAdRepo;

    @Autowired
    private IBatchStockRepository batchStockRepo;




    // valida se o warehouse é valido
    private void isWarehouseValid(long id){
        if(warehouseRepo.findById(id).isEmpty()){
            throw new BadRequestException("Wharehouse doesn't exist!");
        }
    }

    private void isSectionValid(Section section){
        if(section == null) {
            throw new BadRequestException("Section doesn't exist!");
        }
    }

    private void isSectionCapacityValid(List<BatchStockRequestDto> batchStockList, double sectionCapacity){
        double sumBatchStocksVolume = batchStockList.stream().mapToDouble(BatchStockRequestDto::getVolume).sum();

        if(sumBatchStocksVolume > sectionCapacity) {
            throw new BadRequestException("Section capacity is lower than batchstocks!");
        }
    }

    private void isAlltypeProductsValid(List<BatchStockRequestDto> batchStockList, Category sectionCategory){
        batchStockList.forEach(bs -> {
            SellerAd sellerAd = sellerAdRepo.findById(bs.getSellerAdId()).get();
            if(!sellerAd.getProduct().getCategory().equals(sectionCategory)){
                throw new BadRequestException("Product type doesn't exist!");

            }
        });
    }

    // atualiza ou insere um inbound order
    private List<BatchStock> saveInboundOrder(InboundOrderRequestDto inboundOrderRequestDto) {
        Section section = sectionRepo.findById(inboundOrderRequestDto.getSection()).orElseThrow(() -> new BadRequestException("Invalid section!"));
        isSectionValid(section);

        long warehouseId = section.getWarehouse().getWarehouseId();
        isWarehouseValid(warehouseId);

        isSectionCapacityValid(inboundOrderRequestDto.getBatchStockList(), section.getSectionCapacity());

        isAlltypeProductsValid(inboundOrderRequestDto.getBatchStockList(), section.getCategory());

        // TODO: Testar com cascade
        List<BatchStock> newBatchStockList = inboundOrderRequestDto.getBatchStockList().stream().map(batchStockRequest -> {
            SellerAd sellerAdFound = sellerAdRepo.findById(batchStockRequest.getSellerAdId()).get();
            return new BatchStock(batchStockRequest, sellerAdFound);
        }).collect(Collectors.toList());

        InboundOrder inboudOrder = InboundOrder.builder()
                .orderDate(LocalDate.now())
                .batchStockList(newBatchStockList)
                .section(section)
                .build();

        inboudOrder.setInboundOrderId(0L);

        InboundOrder inboundOrderSaved = inboundOrderRepo.save(inboudOrder);

        // TODO: transformar para o formato de response
        return inboundOrderSaved.getBatchStockList();

//        List<BatchStock> batchStocksSaved = new ArrayList<BatchStock>();
//
//        newBatchStockList.forEach(batchStock -> {
//            batchStocksSaved.add(batchStockRepo.save(batchStock));
//        });
//
//        return batchStocksSaved;
    }

    @Override
    public List<BatchStock> insertNewInboundOrder(InboundOrderRequestDto newInboundOrder) {
        if(newInboundOrder.getId() != 0){
            throw new BadRequestException("Order already registered, to update an order use the route /put");
        }
        return saveInboundOrder(newInboundOrder);

    }

    @Override
    public List<BatchStock> updateNewInboundOrder(InboundOrderRequestDto newInboundOrder) {
        // TODO: Caso tenha um método para buscar order por ID utilizar aqui, dentro dele já tem a exception sendo lançada
        Optional<InboundOrder> inboundFound = inboundOrderRepo.findById(newInboundOrder.getId());
        if(inboundFound.isPresent()){
            return saveInboundOrder(newInboundOrder);
        }
        throw new BadRequestException("ID is required to update an order, to insert a new order use the route /post");
    }
}
