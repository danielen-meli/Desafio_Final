package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerId;

    @Column(nullable = false)
    private String sellerName;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private List<SellerAd> sellerAdList;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private List<SellerHistory> sellerHistory;

    @OneToOne
    private User user;
}
