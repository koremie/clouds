package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class MachineModel {
    @Id
    @Column(name = "name", length = 50)
    private String name;
    @Basic
    @Column(name = "company", length = 50, nullable = false)
    private String company;
    @OneToMany(mappedBy = "machineModel")
    private List<VendingMachine> vendingMachines;
}
