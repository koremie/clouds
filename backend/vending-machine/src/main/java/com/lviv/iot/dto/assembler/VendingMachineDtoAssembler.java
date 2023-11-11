package com.lviv.iot.dto.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.VendingMachineController;
import com.lviv.iot.domain.VendingMachine;
import com.lviv.iot.dto.VendingMachineDto;

@Component
public class VendingMachineDtoAssembler implements RepresentationModelAssembler<VendingMachine, VendingMachineDto> {
    @Override
    public VendingMachineDto toModel(VendingMachine entity) {
        VendingMachineDto vendingMachineDto = VendingMachineDto.builder()
                .id(entity.getId())
                .gps(entity.getGps())
                .contactNumber(entity.getContactNumber())
                .machineModel(entity.getMachineModel().getName())
                .addressId(entity.getAddress().getId())
                .build();
        Link selfLink = linkTo(methodOn(VendingMachineController.class).getVendingMachine(vendingMachineDto.getId())).withSelfRel();
        vendingMachineDto.add(selfLink);
        return vendingMachineDto;
    }

    @Override
    public CollectionModel<VendingMachineDto> toCollectionModel(Iterable<? extends VendingMachine> entities) {
        CollectionModel<VendingMachineDto> vendingMachineDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(VendingMachineController.class).getAllVendingMachines()).withSelfRel();
        vendingMachineDtos.add(selfLink);
        return vendingMachineDtos;
    }

    public CollectionModel<VendingMachineDto> toCollectionModel(Iterable<? extends VendingMachine> entities, Link link) {
        CollectionModel<VendingMachineDto> vendingMachineDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        vendingMachineDtos.add(link);
        return vendingMachineDtos;
    }
}