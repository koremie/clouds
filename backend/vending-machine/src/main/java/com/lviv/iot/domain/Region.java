package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Region {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
    @OneToMany(mappedBy = "region")
    private List<City> cities;
}
