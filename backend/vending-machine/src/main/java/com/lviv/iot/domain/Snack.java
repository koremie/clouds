package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Snack {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    @Basic
    @Column(name = "net_weight")
    private Float netWeight;
    @ManyToOne
    @JoinColumn(name = "trademark_name", referencedColumnName = "name")
    private Trademark trademark;
    @OneToMany(mappedBy = "snack")
    private List<Menu> menus;
}
