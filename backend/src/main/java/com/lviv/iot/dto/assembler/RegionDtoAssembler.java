package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.RegionController;
import com.lviv.iot.domain.Region;
import com.lviv.iot.dto.RegionDto;

@Component
public class RegionDtoAssembler implements RepresentationModelAssembler<Region, RegionDto> {
    @Override
    public RegionDto toModel(Region entity) {
        RegionDto regionDto = RegionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .country(entity.getCountry().getName())
                .build();
        Link selfLink = linkTo(methodOn(RegionController.class).getRegion(regionDto.getId())).withSelfRel();
        regionDto.add(selfLink);
        return regionDto;
    }

    @Override
    public CollectionModel<RegionDto> toCollectionModel(Iterable<? extends Region> entities) {
        CollectionModel<RegionDto> regionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RegionController.class).getAllRegions()).withSelfRel();
        regionDtos.add(selfLink);
        return regionDtos;
    }

    public CollectionModel<RegionDto> toCollectionModel(Iterable<? extends Region> entities, Link link) {
        CollectionModel<RegionDto> regionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        regionDtos.add(link);
        return regionDtos;
    }
}