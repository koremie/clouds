package com.lviv.iot.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MenuId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "vending_machine_id", referencedColumnName = "id")
    private VendingMachine vendingMachine;
    
    @Column(name = "slot_number")
    private Integer slotNumber;
}