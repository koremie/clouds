package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;
    @OneToMany(mappedBy = "city")
    private List<Address> addresses;
}
