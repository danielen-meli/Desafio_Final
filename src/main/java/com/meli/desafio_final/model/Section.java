package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meli.desafio_final.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnoreProperties("section")
    private List<InboundOrder> inboundOrder;
}
