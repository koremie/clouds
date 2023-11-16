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
@Relation(itemRelation = "menu", collectionRelation = "menus")
public class MenuDto extends RepresentationModel<MenuDto> {
    private final Integer vendingMachineId;
    private final Integer slotNumber;
    private final Integer quantity;
    private final Integer pricePerUnit;
    private final Integer snackId;
}