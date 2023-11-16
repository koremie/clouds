package com.lviv.iot.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer id) {
        super("Couldn't find address with id: " + id);
    }
}