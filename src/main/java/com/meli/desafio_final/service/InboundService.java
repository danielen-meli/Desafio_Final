package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.BatchStockResponseDto;
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


    /**
     * Validated if the warehouse is ok.
     * @param id search by the id.
     */
    private void isWarehouseValid(long id){
        if(warehouseRepo.findById(id).isEmpty()){
            throw new BadRequestException("Wharehouse doesn't exist!");
        }
    }

    /**
     * Validated if the section is ok.
     * @param section to be validated.
     */
    private void isSectionValid(Section section){
        if(section == null) {
            throw new BadRequestException("Section doesn't exist!");
        }
    }

    /**
     * Validated the section capacity to verify
     * @param batchStockList can be received
     * @param sectionCapacity is ok
     * @return the information about the section
     */
    private double isSectionCapacityValid(List<BatchStockRequestDto> batchStockList, double sectionCapacity){
        double sumBatchStocksVolume = batchStockList.stream().mapToDouble(bs -> {
            if(bs.getBatchStockId() > 0){
                return 0;
            }
            return bs.getVolume();
        }).sum();

        if(sumBatchStocksVolume > sectionCapacity) {
            throw new BadRequestException("Section capacity is lower than batchstocks!");
        }

        return sumBatchStocksVolume;
    }

    /**
     * Checks if the product is in the correct section.
     * @param batchStockList the list of products
     * @param sectionCategory the information about the section
     */
    private void isAlltypeProductsValid(List<BatchStockRequestDto> batchStockList, Category sectionCategory){
        batchStockList.forEach(bs -> {
            SellerAd sellerAd = sellerAdRepo.findById(bs.getSellerAdId()).orElseThrow(() ->
                    new BadRequestException("Invalid Seller Ad!"));
            if(!sellerAd.getProduct().getCategory().equals(sectionCategory)){
                throw new BadRequestException("Product type doesn't exist!");

            }
        });
    }

    /**
     * Insert or make changes in the  inbound order
     * @param inboundOrderRequestDto - data about the products sent to the section
     * @return if is possible receive the products in the section doing all necessary checks before.
     */
    private List<BatchStock> saveInboundOrder(InboundOrderRequestDto inboundOrderRequestDto) {
        Section section = sectionRepo.findById(inboundOrderRequestDto.getSection()).orElseThrow(() ->
                new BadRequestException("Invalid section!"));
        isSectionValid(section);

        long warehouseId = section.getWarehouse().getWarehouseId();
        isWarehouseValid(warehouseId);

        double sectionActualCapacity = section.getSectionCapacity();

        double batchStocksTotalVolume = isSectionCapacityValid(inboundOrderRequestDto.getBatchStockList(), sectionActualCapacity);

        isAlltypeProductsValid(inboundOrderRequestDto.getBatchStockList(), section.getCategory());

        InboundOrder inboudOrder = InboundOrder.builder()
                .orderDate(LocalDate.now())
                .inboundOrderId(inboundOrderRequestDto.getId())  // necessario pois sem o id ele nao consegue atualizar
                .section(section)
                .build();

        InboundOrder inboundOrderSaved = inboundOrderRepo.save(inboudOrder);

        List<BatchStock> newBatchStockList = inboundOrderRequestDto.getBatchStockList().stream().map(batchStockRequest -> {
            SellerAd sellerAdFound = sellerAdRepo.findById(batchStockRequest.getSellerAdId()).get();
            return new BatchStock(batchStockRequest, sellerAdFound, inboundOrderSaved);
        }).collect(Collectors.toList());


        List<BatchStock> batchStocksSaved = new ArrayList<>();

        newBatchStockList.forEach(batchStock -> {
            batchStocksSaved.add(batchStockRepo.save(batchStock));
        });

        section.setSectionCapacity(sectionActualCapacity - batchStocksTotalVolume);
        sectionRepo.save(section);
        return batchStocksSaved;
    }

    /**
     * Method to save the correct information
     * @param batchStocksSaved information to be saved
     * @return a correct data in SQL.
     */
    private InboundOrderResponseDto convertBatchStockToResponse(List<BatchStock> batchStocksSaved) {
        List<BatchStockResponseDto> batchStocksResponseDto = batchStocksSaved.stream().map(BatchStockResponseDto::new).collect(Collectors.toList());
        return InboundOrderResponseDto.builder()
                .batchStockList(batchStocksResponseDto)
                .build();
    }

    /**
     * Method to insert a new inbound order.
     * @param newInboundOrder new products
     * @return a possibility or not of receive the products
     */
    @Override
    public InboundOrderResponseDto insertNewInboundOrder(InboundOrderRequestDto newInboundOrder) {
        if(newInboundOrder.getId() != 0){
            throw new BadRequestException("Order already registered, to update an order use the route /put");
        }
        List<BatchStock> batchStocksSaved = saveInboundOrder(newInboundOrder);
        return convertBatchStockToResponse(batchStocksSaved);
    }

    /**
     * Method to update an existent inbound order.
     * @param newInboundOrder - list with the changes
     * @return the update of information, ou a intruction.
     */
    @Override
    public InboundOrderResponseDto updateNewInboundOrder(InboundOrderRequestDto newInboundOrder) {
        Optional<InboundOrder> inboundFound = inboundOrderRepo.findById(newInboundOrder.getId());
        if(inboundFound.isPresent()){
            List<BatchStock> batchStocksSaved = saveInboundOrder(newInboundOrder);
            return convertBatchStockToResponse(batchStocksSaved);
        }
        throw new BadRequestException("ID is required to update an order, to insert a new order use the route /post");
    }
}
