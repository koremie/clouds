package com.lviv.iot.exception;

public class VendingMachineNotFoundException extends RuntimeException {
    public VendingMachineNotFoundException(Integer id) {
        super("Couldn't find vending machine with id: " + id);
    }
}