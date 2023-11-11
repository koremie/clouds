package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.RestockController;
import com.lviv.iot.domain.Restock;
import com.lviv.iot.dto.RestockDto;

@Component
public class RestockDtoAssembler implements RepresentationModelAssembler<Restock, RestockDto> {
    @Override
    public RestockDto toModel(Restock entity) {
        RestockDto restockDto = RestockDto.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .date(entity.getDate())
                .vendingMachineId(entity.getMenu().getId().getVendingMachine().getId())
                .slotNumber(entity.getMenu().getId().getSlotNumber())
                .serviceStaffId(entity.getServiceStaff().getId())
                .build();
        Link selfLink = linkTo(methodOn(RestockController.class).getRestock(restockDto.getId())).withSelfRel();
        restockDto.add(selfLink);
        return restockDto;
    }

    @Override
    public CollectionModel<RestockDto> toCollectionModel(Iterable<? extends Restock> entities) {
        CollectionModel<RestockDto> restockDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RestockController.class).getAllRestocks()).withSelfRel();
        restockDtos.add(selfLink);
        return restockDtos;
    }

    public CollectionModel<RestockDto> toCollectionModel(Iterable<? extends Restock> entities, Link link) {
        CollectionModel<RestockDto> restockDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        restockDtos.add(link);
        return restockDtos;
    }
}