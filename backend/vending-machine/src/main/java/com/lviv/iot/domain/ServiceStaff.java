package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class ServiceStaff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Basic
    @Column(name = "last_name", length = 50, nullable = false)
    private String Lastname;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "serviceStaff")
    private List<Restock> restocks;
}
