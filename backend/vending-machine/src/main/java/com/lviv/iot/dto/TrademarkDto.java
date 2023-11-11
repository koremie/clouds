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
@Relation(itemRelation = "trademark", collectionRelation = "trademarks")
public class TrademarkDto extends RepresentationModel<AddressDto> {
    private final String name;
}