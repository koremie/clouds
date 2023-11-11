package com.lviv.iot.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "address", collectionRelation = "addresses")
public class AddressDto extends RepresentationModel<AddressDto> {
    private final Integer id;
    private final String street;
    private final String houseNumber;
    private final Integer appartmentNumber;
    private final String city;
    private final String region;
}