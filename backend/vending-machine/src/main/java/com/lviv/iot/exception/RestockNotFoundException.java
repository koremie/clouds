package com.lviv.iot.exception;

public class RestockNotFoundException extends RuntimeException {
    public RestockNotFoundException(Integer id) {
        super("Couldn't find restock with id: " + id);
    }
}