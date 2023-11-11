package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.AddressController;
import com.lviv.iot.domain.Address;
import com.lviv.iot.dto.AddressDto;

@Component
public class AddressDtoAssembler implements RepresentationModelAssembler<Address, AddressDto> {
    @Override
    public AddressDto toModel(Address entity) {
        AddressDto addressDto = AddressDto.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .houseNumber(entity.getHouseNumber())
                .appartmentNumber(entity.getAppartmentNumber())
                .city(entity.getCity().getName())
                .region(entity.getCity().getRegion().getName())
                .build();
        Link selfLink = linkTo(methodOn(AddressController.class).getAddress(addressDto.getId())).withSelfRel();
        addressDto.add(selfLink);
        return addressDto;
    }

    @Override
    public CollectionModel<AddressDto> toCollectionModel(Iterable<? extends Address> entities) {
        CollectionModel<AddressDto> addressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AddressController.class).getAllAddresses()).withSelfRel();
        addressDtos.add(selfLink);
        return addressDtos;
    }

    public CollectionModel<AddressDto> toCollectionModel(Iterable<? extends Address> entities, Link link) {
        CollectionModel<AddressDto> addressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        addressDtos.add(link);
        return addressDtos;
    }
}