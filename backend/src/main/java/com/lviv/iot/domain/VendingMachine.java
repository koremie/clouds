package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class VendingMachine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "gps", length = 50, nullable = false)
    private String gps;
    @Basic
    @Column(name = "contact_number")
    private String contactNumber;
    @ManyToOne
    @JoinColumn(name = "machine_model_name", referencedColumnName = "name")
    private MachineModel machineModel;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "id.vendingMachine")
    private List<Menu> menus;
}
