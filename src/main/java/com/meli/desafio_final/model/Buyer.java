package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Buyer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long buyerId;
    @OneToOne
    private User user;
}
