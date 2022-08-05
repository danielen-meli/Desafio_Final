package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.engine.jdbc.batch.spi.Batch;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SellerAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerAdId;
    private double price;
    @OneToMany(mappedBy = "sellerAd")
    private List<BatchStock> batchStockId;

    @ManyToMany
     @JoinTable(name = "SellerAd_ShopOrder",
       joinColumns = @JoinColumn(name = "sellerAdId", referencedColumnName = "sellerAdId"),
   inverseJoinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderId"))
    @JsonIgnoreProperties("sellerAd")
    private List<ShopOrder> shopOrder;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;
    @ManyToOne
    private Product product;

    // @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
}
