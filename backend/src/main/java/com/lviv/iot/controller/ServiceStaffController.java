package com.lviv.iot.controller;

import com.lviv.iot.domain.ServiceStaff;
import com.lviv.iot.dto.ServiceStaffDto;
import com.lviv.iot.dto.assembler.ServiceStaffDtoAssembler;
import com.lviv.iot.service.ServiceStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/service-staff")
public class ServiceStaffController {
    @Autowired
    private ServiceStaffService serviceStaffService;
    @Autowired
    private ServiceStaffDtoAssembler serviceStaffDtoAssembler;

    @GetMapping(value = "/{serviceStaffId}")
    public ResponseEntity<ServiceStaffDto> getServiceStaff(@PathVariable Integer serviceStaffId) {
        ServiceStaff serviceStaff = serviceStaffService.findById(serviceStaffId);
        ServiceStaffDto serviceStaffDto = serviceStaffDtoAssembler.toModel(serviceStaff);
        return new ResponseEntity<>(serviceStaffDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ServiceStaffDto>> getAllServiceStaffs() {
        List<ServiceStaff> serviceStaffs = serviceStaffService.findAll();
        CollectionModel<ServiceStaffDto> serviceStaffDtos = serviceStaffDtoAssembler.toCollectionModel(serviceStaffs);
        return new ResponseEntity<>(serviceStaffDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ServiceStaffDto> addServiceStaff(@RequestBody ServiceStaff serviceStaff) {
        ServiceStaff newServiceStaff = serviceStaffService.create(serviceStaff);
        ServiceStaffDto serviceStaffDto = serviceStaffDtoAssembler.toModel(newServiceStaff);
        return new ResponseEntity<>(serviceStaffDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{serviceStaffId}")
    public ResponseEntity<?> updateServiceStaff(@RequestBody ServiceStaff uServiceStaff, @PathVariable Integer serviceStaffId) {
        serviceStaffService.update(serviceStaffId, uServiceStaff);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{serviceStaffId}")
    public ResponseEntity<?> deleteServiceStaff(@PathVariable Integer serviceStaffId) {
        serviceStaffService.delete(serviceStaffId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}