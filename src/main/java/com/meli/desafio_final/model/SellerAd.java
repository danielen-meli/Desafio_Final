package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.jdbc.batch.spi.Batch;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerAdId;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "sellerAd")
    @JsonIgnoreProperties("sellerAd")
    private List<BatchStock> batchStockId;

    @ManyToOne
    @JoinColumn(name = "sellerId")

    @JsonIgnoreProperties("sellerAdList")
    private Seller seller;

    @ManyToOne
    private Product product;

}
