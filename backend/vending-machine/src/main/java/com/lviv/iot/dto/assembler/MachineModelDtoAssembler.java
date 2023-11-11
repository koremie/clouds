package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.MachineModelController;
import com.lviv.iot.domain.MachineModel;
import com.lviv.iot.dto.MachineModelDto;

@Component
public class MachineModelDtoAssembler implements RepresentationModelAssembler<MachineModel, MachineModelDto> {
    @Override
    public MachineModelDto toModel(MachineModel entity) {
        MachineModelDto machineModelDto = MachineModelDto.builder()
                .name(entity.getName())
                .company(entity.getCompany())
                .build();
        Link selfLink = linkTo(methodOn(MachineModelController.class).getMachineModel(machineModelDto.getName())).withSelfRel();
        machineModelDto.add(selfLink);
        return machineModelDto;
    }

    @Override
    public CollectionModel<MachineModelDto> toCollectionModel(Iterable<? extends MachineModel> entities) {
        CollectionModel<MachineModelDto> addressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MachineModelController.class).getAllMachineModels()).withSelfRel();
        addressDtos.add(selfLink);
        return addressDtos;
    }

    public CollectionModel<MachineModelDto> toCollectionModel(Iterable<? extends MachineModel> entities, Link link) {
        CollectionModel<MachineModelDto> machineModelDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        machineModelDtos.add(link);
        return machineModelDtos;
    }
}