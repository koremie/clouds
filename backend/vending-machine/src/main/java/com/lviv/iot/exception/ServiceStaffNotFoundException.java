package com.lviv.iot.exception;

public class ServiceStaffNotFoundException extends RuntimeException {
    public ServiceStaffNotFoundException(Integer id) {
        super("Couldn't find service staff with id: " + id);
    }
}