package com.meli.desafio_final.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Buyer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long buyerId;

    @OneToOne
    private User user;
}
