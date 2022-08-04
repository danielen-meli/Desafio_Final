package com.meli.desafio_final.model;

import lombok.Data;
import org.hibernate.engine.jdbc.batch.spi.Batch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class SellerAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerAdId;
    private double price;
    private BatchStock batchStockId;
    private Seller sellerId;
}
