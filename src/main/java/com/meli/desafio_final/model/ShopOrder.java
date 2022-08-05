package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meli.desafio_final.model.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column
    private Status status;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopOrder_id")
    private List<ShopOrderItem> shopOrderItem;

//    @ManyToMany(mappedBy = "shopOrder")
//    @JsonIgnoreProperties("shopOrder")
//    private List<SellerAd> sellerAd;

    @ManyToOne
    private Buyer buyer;

}
