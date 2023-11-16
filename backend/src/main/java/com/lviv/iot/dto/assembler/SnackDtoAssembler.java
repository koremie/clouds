package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.SnackController;
import com.lviv.iot.domain.Snack;
import com.lviv.iot.dto.SnackDto;

@Component
public class SnackDtoAssembler implements RepresentationModelAssembler<Snack, SnackDto> {
    @Override
    public SnackDto toModel(Snack entity) {
        SnackDto snackDto = SnackDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .netWeight(entity.getNetWeight())
                .trademark(entity.getTrademark().getName())
                .build();
        Link selfLink = linkTo(methodOn(SnackController.class).getSnack(snackDto.getId())).withSelfRel();
        snackDto.add(selfLink);
        return snackDto;
    }

    @Override
    public CollectionModel<SnackDto> toCollectionModel(Iterable<? extends Snack> entities) {
        CollectionModel<SnackDto> snackDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SnackController.class).getAllSnacks()).withSelfRel();
        snackDtos.add(selfLink);
        return snackDtos;
    }

    public CollectionModel<SnackDto> toCollectionModel(Iterable<? extends Snack> entities, Link link) {
        CollectionModel<SnackDto> snackDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        snackDtos.add(link);
        return snackDtos;
    }
}