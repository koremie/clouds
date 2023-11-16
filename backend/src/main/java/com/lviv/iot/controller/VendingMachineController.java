package com.lviv.iot.controller;

import com.lviv.iot.domain.Menu;
import com.lviv.iot.domain.VendingMachine;
import com.lviv.iot.dto.MenuDto;
import com.lviv.iot.dto.VendingMachineDto;
import com.lviv.iot.dto.assembler.MenuDtoAssembler;
import com.lviv.iot.dto.assembler.VendingMachineDtoAssembler;
import com.lviv.iot.service.VendingMachineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vending-machines")
public class VendingMachineController {
    @Autowired
    private VendingMachineService vendingMachineService;
    @Autowired
    private VendingMachineDtoAssembler vendingMachineDtoAssembler;
    @Autowired
    private MenuDtoAssembler menuDtoAssembler;

    @GetMapping(value = "/{vendingMachineId}")
    public ResponseEntity<VendingMachineDto> getVendingMachine(@PathVariable Integer vendingMachineId) {
        VendingMachine vendingMachine = vendingMachineService.findById(vendingMachineId);
        VendingMachineDto vendingMachineDto = vendingMachineDtoAssembler.toModel(vendingMachine);
        return new ResponseEntity<>(vendingMachineDto, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{vendingMachineId}/menu")
    public ResponseEntity<CollectionModel<MenuDto>> getMenuForVendingMachine(@PathVariable Integer vendingMachineId) {
        List<Menu> menus = vendingMachineService.findMenuById(vendingMachineId);
        Link selfLink = linkTo(methodOn(VendingMachineController.class).getMenuForVendingMachine(vendingMachineId)).withSelfRel();
        CollectionModel<MenuDto> menuDtos = menuDtoAssembler.toCollectionModel(menus, selfLink);
        return new ResponseEntity<>(menuDtos, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<VendingMachineDto>> getAllVendingMachines() {
        List<VendingMachine> vendingMachines = vendingMachineService.findAll();
        CollectionModel<VendingMachineDto> vendingMachineDtos = vendingMachineDtoAssembler
                .toCollectionModel(vendingMachines);
        return new ResponseEntity<>(vendingMachineDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<VendingMachineDto> addVendingMachine(@RequestBody VendingMachine vendingMachine) {
        VendingMachine newVendingMachine = vendingMachineService.create(vendingMachine);
        VendingMachineDto vendingMachineDto = vendingMachineDtoAssembler.toModel(newVendingMachine);
        return new ResponseEntity<>(vendingMachineDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{vendingMachineId}")
    public ResponseEntity<?> updateVendingMachine(@RequestBody VendingMachine uVendingMachine,
            @PathVariable Integer vendingMachineId) {
        vendingMachineService.update(vendingMachineId, uVendingMachine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vendingMachineId}")
    public ResponseEntity<?> deleteVendingMachine(@PathVariable Integer vendingMachineId) {
        vendingMachineService.delete(vendingMachineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}