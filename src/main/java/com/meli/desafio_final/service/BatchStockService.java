package com.meli.desafio_final.service;


import com.meli.desafio_final.model.enums.OrderBy;
import com.meli.desafio_final.repository.IBatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;
import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.IInboundOrderRepository;
import com.meli.desafio_final.repository.ISectionRepository;


import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchStockService implements IBatchStockService {

    @Autowired
    private IBatchStockRepository batchStockRepository;

    @Autowired
    private IInboundOrderRepository inboundOrderRepository;

    @Autowired
    private ISectionRepository sectionRepository;

    private List<BatchStock> filterBatchStockCurrentQuantity(List<BatchStock> batchStockList){
        return batchStockList.stream().filter(bs -> {
            int batchStockCurrentQuantity = bs.getCurrentQuantity();
            return batchStockCurrentQuantity != 0;
        }).collect(Collectors.toList());
    }

    private List<BatchStock> filterBatchStocksByDueDate(List<BatchStock> batchStocks, int numberOfDays) {
        LocalDate actualDate = LocalDate.now();
        LocalDate lastDateToDue = actualDate.plusDays(numberOfDays);
        return batchStocks.stream().filter(bs -> {
            LocalDate batchStockDueDate = bs.getDueDate();
            return (batchStockDueDate.isAfter(actualDate) && batchStockDueDate.isBefore(lastDateToDue));
        }).collect(Collectors.toList());
    }

    @Override
    public List<BatchStockByDueDateResponseDto> getBatchStocksByDueDate(int numberOfDays, long sectionId) {

        Section sectionSelected = sectionRepository.findById(sectionId).orElseThrow(() -> new BadRequestException("Section ID invalid!"));
        Category sectionCategory = sectionSelected.getCategory();

        List<BatchStock> batchStockList = new ArrayList<>();
        List<InboundOrder> inboundOrderBySectionList = inboundOrderRepository.findAllBySectionSectionId(sectionId);

        inboundOrderBySectionList.forEach(io -> batchStockList.addAll(io.getBatchStockList()));

        List<BatchStock> batchStockListFilterCurrentQuantity = filterBatchStockCurrentQuantity(batchStockList);

        List<BatchStock> batchStockListFilterByDueDate = filterBatchStocksByDueDate(batchStockListFilterCurrentQuantity, numberOfDays);

        return batchStockListFilterByDueDate.stream().map(bs -> new BatchStockByDueDateResponseDto(bs, sectionCategory)).collect(Collectors.toList());

    };

    private List<BatchStock> orderBy(List<BatchStock> batchStockListFilterByDueDate, String orderType){
        return batchStockListFilterByDueDate.stream().sorted((b1,b2) -> {
            if(orderType.equalsIgnoreCase("asc")) return b1.getDueDate().compareTo(b2.getDueDate());
            else if(orderType.equalsIgnoreCase("desc")) return b2.getDueDate().compareTo(b1.getDueDate());
            else throw new BadRequestException("Invalid order type");
        }).collect(Collectors.toList());
    }

    @Override
    public List<BatchStockByDueDateResponseDto> getBatchStocksFilteredBy(int numberOfDays, Category category, String orderType) {
        List<BigInteger> sectionsIdsByCategory = sectionRepository.getSectionsIdsByCategory(category);
        List<InboundOrder> inboundOrdersByAllSections = inboundOrderRepository.getInboundOrder(sectionsIdsByCategory);

        List<BatchStock> batchStockList = new ArrayList<>();
        inboundOrdersByAllSections.forEach(io -> batchStockList.addAll(io.getBatchStockList()));

        List<BatchStock> batchStockListFilterCurrentQuantity = filterBatchStockCurrentQuantity(batchStockList);

        List<BatchStock> batchStockListFilterByDueDate = filterBatchStocksByDueDate(batchStockListFilterCurrentQuantity, numberOfDays);

        List<BatchStock> batchStocksOrderByDueDate = orderBy(batchStockListFilterByDueDate, orderType);

        return batchStocksOrderByDueDate.stream().map(bs -> new BatchStockByDueDateResponseDto(bs, category)).collect(Collectors.toList());
    }

    @Override
    public List<BatchStockDto> getProductsInStock(long productId) {
        List<BatchStockDto> listDtoByCategory = batchStockRepository.findAll().stream().
                map(BatchStockDto::new).filter(p -> p.getProductId() == productId).
                collect(Collectors.toList());

        if(listDtoByCategory.isEmpty()){
            throw new NotFoundException("Não existem produtos em estoque.");
        }

        return listDtoByCategory;
    }

    @Override
    public List<BatchStockDto> getProductsInStockOrdered(long productId, OrderBy orderBy) {
        List<BatchStockDto> listDtoByCategoryOrdered = batchStockRepository.findAll().stream().
                map(BatchStockDto::new).filter(p -> p.getProductId() == productId).collect(Collectors.toList());

        switch (orderBy){
            case L://ordenando por numero do lote
                return listDtoByCategoryOrdered.stream().
                        sorted(Comparator.comparingLong(BatchStockDto::getBatchStockId)).
                        collect(Collectors.toList());
            case Q://ordenando por quantidade atual
                return listDtoByCategoryOrdered.stream().
                        sorted(Comparator.comparingDouble(BatchStockDto::getCurrentQuantity)).
                        collect(Collectors.toList());
            case V://ordenando por vencimento
                return listDtoByCategoryOrdered.stream().
                        sorted(Comparator.comparing(BatchStockDto::getDueDate)).
                        collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Categoria inválida.");
        }
    }
}
