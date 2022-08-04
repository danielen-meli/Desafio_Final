package com.meli.desafio_final.model;

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
//    @JoinTable(name = "SellerAd_ShopOrder",
//            joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderId"),
//            inverseJoinColumns = @JoinColumn(name = "sellerAdId", referencedColumnName = "sellerAdId"))
   // @JsonIgnoreProperties("SellerAd")
    private List<ShopOrder> shopOrder;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;

    // @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
}
