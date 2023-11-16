package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Menu {
    @EmbeddedId
    private MenuId id;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "price_per_unit")
    private Integer pricePerUnit;
    @ManyToOne
    @JoinColumn(name = "snack_id", referencedColumnName = "id")
    private Snack snack;
    @OneToMany(mappedBy = "menu")
    private List<Restock> restocks;
}