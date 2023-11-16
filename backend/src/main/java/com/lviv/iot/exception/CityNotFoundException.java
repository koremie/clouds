package com.lviv.iot.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(Integer id) {
        super("Couldn't find city with id: " + id);
    }
}