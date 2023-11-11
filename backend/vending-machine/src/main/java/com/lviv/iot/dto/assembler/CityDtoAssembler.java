package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.CityController;
import com.lviv.iot.domain.City;
import com.lviv.iot.dto.CityDto;

@Component
public class CityDtoAssembler implements RepresentationModelAssembler<City, CityDto> {
    @Override
    public CityDto toModel(City entity) {
        CityDto cityDto = CityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .region(entity.getRegion().getName())
                .build();
        Link selfLink = linkTo(methodOn(CityController.class).getCity(cityDto.getId())).withSelfRel();
        cityDto.add(selfLink);
        return cityDto;
    }

    @Override
    public CollectionModel<CityDto> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityDto> cityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();
        cityDtos.add(selfLink);
        return cityDtos;
    }

    public CollectionModel<CityDto> toCollectionModel(Iterable<? extends City> entities, Link link) {
        CollectionModel<CityDto> cityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        cityDtos.add(link);
        return cityDtos;
    }
}