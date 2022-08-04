package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class InboundOrder {
    @Id
    private int orderNumber;
    private LocalDate orderDate;
    private Section section;
    private List<BatchStock> batchStockList;

}
