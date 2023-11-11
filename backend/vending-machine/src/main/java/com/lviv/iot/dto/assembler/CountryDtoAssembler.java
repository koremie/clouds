package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.CountryController;
import com.lviv.iot.domain.Country;
import com.lviv.iot.dto.CountryDto;

@Component
public class CountryDtoAssembler implements RepresentationModelAssembler<Country, CountryDto> {
    @Override
    public CountryDto toModel(Country entity) {
        CountryDto countryDto = CountryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(CountryController.class).getCountry(countryDto.getId())).withSelfRel();
        countryDto.add(selfLink);
        return countryDto;
    }

    @Override
    public CollectionModel<CountryDto> toCollectionModel(Iterable<? extends Country> entities) {
        CollectionModel<CountryDto> countryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        countryDtos.add(selfLink);
        return countryDtos;
    }

    public CollectionModel<CountryDto> toCollectionModel(Iterable<? extends Country> entities, Link link) {
        CollectionModel<CountryDto> countryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        countryDtos.add(link);
        return countryDtos;
    }
}