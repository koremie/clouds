package com.lviv.iot.exception;

import com.lviv.iot.domain.VendingMachine;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(Integer slotNumber, VendingMachine vendingMachine) {
        super("Couldn't find menu slot" + slotNumber + " in vending machine with id: " + vendingMachine.getId());
    }
}