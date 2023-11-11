package com.lviv.iot.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.sql.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "restock", collectionRelation = "restocks")
public class RestockDto extends RepresentationModel<AddressDto> {
    private final Integer id;
    private final Integer quantity;
    private final Date date;
    private final Integer vendingMachineId;
    private final Integer slotNumber;
    private final Integer serviceStaffId;
}