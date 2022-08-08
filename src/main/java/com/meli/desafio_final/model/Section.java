package com.meli.desafio_final.model;

import com.meli.desafio_final.model.enums.Category;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Section {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sectionId;

    @ManyToOne
    private Warehouse warehouse;

    @Column(nullable = false)
    private double sectionTemperature;

    @Column(nullable = false)
    private double sectionCapacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "section")
    private List<InboundOrder> inboundOrder;
}
