package com.lviv.iot.exception;

public class SnackNotFoundException extends RuntimeException {
    public SnackNotFoundException(Integer id) {
        super("Couldn't find snack with id: " + id);
    }
}