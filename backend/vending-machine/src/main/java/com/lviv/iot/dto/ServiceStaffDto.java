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
@Relation(itemRelation = "serviceStaff", collectionRelation = "serviceStaffs")
public class ServiceStaffDto extends RepresentationModel<AddressDto> {
    private final Integer id;
    private final String name;
    private final String lastName;
    private final Integer addressId;
}