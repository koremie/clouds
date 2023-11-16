package com.lviv.iot.exception;

public class TrademarkNotFoundException extends RuntimeException {
    public TrademarkNotFoundException(String name) {
        super("Couldn't find trademark named " + name);
    }
}