package com.meli.desafio_final.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Leader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaderId;

    @OneToOne
    private Warehouse warehouse;

    @Column(nullable = false)
    private String leaderName;
}
