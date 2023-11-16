package com.lviv.iot.controller;

import com.lviv.iot.domain.Address;
import com.lviv.iot.dto.AddressDto;
import com.lviv.iot.dto.assembler.AddressDtoAssembler;
import com.lviv.iot.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressDtoAssembler addressDtoAssembler;

    @GetMapping(value = "/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Integer addressId) {
        Address address = addressService.findById(addressId);
        AddressDto addressDto = addressDtoAssembler.toModel(address);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AddressDto>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        CollectionModel<AddressDto> addressDtos = addressDtoAssembler.toCollectionModel(addresses);
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AddressDto> addAddress(@RequestBody Address address) {
        Address newAddress = addressService.create(address);
        AddressDto addressDto = addressDtoAssembler.toModel(newAddress);
        return new ResponseEntity<>(addressDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{addressId}")
    public ResponseEntity<?> updateAddress(@RequestBody Address uAddress, @PathVariable Integer addressId) {
        addressService.update(addressId, uAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer addressId) {
        addressService.delete(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}