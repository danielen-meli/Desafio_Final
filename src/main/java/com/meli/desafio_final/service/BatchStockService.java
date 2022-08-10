package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.repository.IInboundOrderRepository;
import com.meli.desafio_final.repository.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
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

    // TODO: Transformar para um BatchStockByDueDateDto
    @Override
    public List<BatchStockByDueDateResponseDto> getBatchStocksByDueDate(int numberOfDays, long sectionId) {
        LocalDate actualDate = LocalDate.now();
        LocalDate lastDateToDue = actualDate.plusDays(numberOfDays);  // Data limite para vencimento
        Section sectionSelected = sectionRepository.findById(sectionId).orElseThrow(() -> new BadRequestException("Section ID invalid!"));
        Category sectionCategory = sectionSelected.getCategory();

        List<BatchStock> batchStockList = new ArrayList<>();
        List<InboundOrder> inboundOrderBySectionList = inboundOrderRepository.findAllBySectionSectionId(sectionId);

        inboundOrderBySectionList.forEach(io -> batchStockList.addAll(io.getBatchStockList()));

        List<BatchStock> batchStockListFilterCurrentQuantity = batchStockList.stream().filter(bs -> {
            int batchStockCurrentQuantity = bs.getCurrentQuantity();
            return batchStockCurrentQuantity != 0;
        }).collect(Collectors.toList());

        List<BatchStock> batchStockListFilterByDueDate = batchStockListFilterCurrentQuantity.stream().filter(bs -> {
            LocalDate batchStockDueDate = bs.getDueDate();
            return (batchStockDueDate.isAfter(actualDate) && batchStockDueDate.isBefore(lastDateToDue));
        }).collect(Collectors.toList());

        return batchStockListFilterByDueDate.stream().map(bs -> new BatchStockByDueDateResponseDto(bs, sectionCategory)).collect(Collectors.toList());

    };
}
