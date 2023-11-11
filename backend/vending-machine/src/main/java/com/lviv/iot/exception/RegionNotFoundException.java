package com.lviv.iot.exception;

public class RegionNotFoundException extends RuntimeException {
    public RegionNotFoundException(Integer id) {
        super("Couldn't find region with id: " + id);
    }
}