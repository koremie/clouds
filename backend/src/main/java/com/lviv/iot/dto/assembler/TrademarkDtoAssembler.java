package com.lviv.iot.dto.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.TrademarkController;
import com.lviv.iot.domain.Trademark;
import com.lviv.iot.dto.TrademarkDto;

@Component
public class TrademarkDtoAssembler implements RepresentationModelAssembler<Trademark, TrademarkDto> {
    @Override
    public TrademarkDto toModel(Trademark entity) {
        TrademarkDto trademarkDto = TrademarkDto.builder()
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(TrademarkController.class).getTrademark(trademarkDto.getName())).withSelfRel();
        trademarkDto.add(selfLink);
        return trademarkDto;
    }

    @Override
    public CollectionModel<TrademarkDto> toCollectionModel(Iterable<? extends Trademark> entities) {
        CollectionModel<TrademarkDto> trademarkDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TrademarkController.class).getAllTrademarks()).withSelfRel();
        trademarkDtos.add(selfLink);
        return trademarkDtos;
    }

    public CollectionModel<TrademarkDto> toCollectionModel(Iterable<? extends Trademark> entities, Link link) {
        CollectionModel<TrademarkDto> trademarkDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        trademarkDtos.add(link);
        return trademarkDtos;
    }
}