package com.meli.desafio_final.model;

import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopOrder_id")
    private List<ShopOrderItem> shopOrderItem;
    // registro no DB é invertido, a lista que armazena o ID da entidade dominante

    @ManyToOne
    private Buyer buyer;

    public ShopOrder(ShopOrderRequestDto shopOrderRequestDto, Buyer buyer, List<ShopOrderItem> shopOrderItem){
        this.status = shopOrderRequestDto.getOrderStatus();
        this.buyer = buyer;
        this.shopOrderItem = shopOrderItem;
    }

    public BigDecimal getValorTotal(){
        Double precoTotal = .0;

        for(ShopOrderItem item: shopOrderItem){
            precoTotal += item.getSellerAd().getPrice() * item.getQuantity();
        }

        return new BigDecimal(precoTotal);
    }
}
