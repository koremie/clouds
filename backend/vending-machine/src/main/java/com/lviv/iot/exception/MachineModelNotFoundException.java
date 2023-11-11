package com.lviv.iot.exception;

public class MachineModelNotFoundException extends RuntimeException {
    public MachineModelNotFoundException(String name) {
        super("Couldn't find machine model named: " + name);
    }
}