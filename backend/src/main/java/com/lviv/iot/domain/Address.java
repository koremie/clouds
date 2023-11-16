package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "street", length = 50, nullable = false)
    private String street;
    @Basic
    @Column(name = "house_number")
    private String houseNumber;
    @Basic
    @Column(name = "appartment_number")
    private Integer appartmentNumber;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
    @OneToMany(mappedBy = "address")
    private List<ServiceStaff> serviceStaffs;
    @OneToMany(mappedBy = "address")
    private List<VendingMachine> vendingMachines;
}
