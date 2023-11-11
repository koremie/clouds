package com.lviv.iot.domain;

import lombok.Data;

import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
public class Restock {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Basic
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="menu_vending_machine_id", referencedColumnName="vending_machine_id"),
        @JoinColumn(name="menu_slot_number", referencedColumnName="slot_number")
        })
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "service_staff_id", referencedColumnName = "id")
    private ServiceStaff serviceStaff;
}
