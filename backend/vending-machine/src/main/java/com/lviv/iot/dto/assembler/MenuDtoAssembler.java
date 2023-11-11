package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;

import com.lviv.iot.controller.MenuController;
import com.lviv.iot.domain.Menu;
import com.lviv.iot.dto.MenuDto;

@Component
public class MenuDtoAssembler implements RepresentationModelAssembler<Menu, MenuDto> {
    @Override
    public MenuDto toModel(Menu entity) {
        MenuDto menuDto = MenuDto.builder()
                .vendingMachineId(entity.getId().getVendingMachine().getId())
                .slotNumber(entity.getId().getSlotNumber())
                .quantity(entity.getQuantity())
                .pricePerUnit(entity.getPricePerUnit())
                .snackId(entity.getSnack().getId())
                .build();
        Link selfLink = linkTo(methodOn(MenuController.class).getMenu(menuDto.getVendingMachineId(), menuDto.getSlotNumber())).withSelfRel();
        menuDto.add(selfLink);
        return menuDto;
    }

    @Override
    public CollectionModel<MenuDto> toCollectionModel(Iterable<? extends Menu> entities) {
        CollectionModel<MenuDto> menuDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MenuController.class).getAllMenus()).withSelfRel();
        menuDtos.add(selfLink);
        return menuDtos;
    }

    public CollectionModel<MenuDto> toCollectionModel(Iterable<? extends Menu> entities, Link link) {
        CollectionModel<MenuDto> menuDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        menuDtos.add(link);
        return menuDtos;
    }
}