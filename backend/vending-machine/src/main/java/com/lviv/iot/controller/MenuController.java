package com.lviv.iot.controller;

import com.lviv.iot.domain.Menu;
import com.lviv.iot.domain.MenuId;
import com.lviv.iot.dto.MenuDto;
import com.lviv.iot.dto.assembler.MenuDtoAssembler;
import com.lviv.iot.service.MenuService;
import com.lviv.iot.service.VendingMachineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDtoAssembler menuDtoAssembler;
    @Autowired
    private VendingMachineService vendingMachineService;

    @GetMapping(value = "/{vendingMachineId}/{slotNumber}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Integer vendingMachineId, @PathVariable Integer slotNumber) {
        MenuId menuId = new MenuId(vendingMachineService.findById(vendingMachineId), slotNumber);
        Menu menu = menuService.findById(menuId);
        MenuDto menuDto = menuDtoAssembler.toModel(menu);
        return new ResponseEntity<>(menuDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<MenuDto>> getAllMenus() {
        List<Menu> menus = menuService.findAll();
        CollectionModel<MenuDto> menuDtos = menuDtoAssembler.toCollectionModel(menus);
        return new ResponseEntity<>(menuDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<MenuDto> addMenu(@RequestBody Menu city) {
        Menu newMenu = menuService.create(city);
        MenuDto menuDto = menuDtoAssembler.toModel(newMenu);
        return new ResponseEntity<>(menuDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{vendingMachineId}/{slotNumber}")
    public ResponseEntity<?> updateMenu(@RequestBody Menu uMenu, @PathVariable Integer vendingMachineId,
            @PathVariable Integer slotNumber) {
        MenuId menuId = new MenuId(vendingMachineService.findById(vendingMachineId), slotNumber);
        menuService.update(menuId, uMenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vendingMachineId}/{slotNumber}")
    public ResponseEntity<?> deleteMenu(@PathVariable Integer vendingMachineId, @PathVariable Integer slotNumber) {
        MenuId menuId = new MenuId(vendingMachineService.findById(vendingMachineId), slotNumber);
        menuService.delete(menuId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}