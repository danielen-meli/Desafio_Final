package com.meli.desafio_final.model;

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

    @Column(nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopOrder_id")
    private List<ShopOrderItem> shopOrderItem;
    // registro no DB é invertido, a lista que armazena o ID da entidade dominante

    @ManyToOne
    private Buyer buyer;

}
