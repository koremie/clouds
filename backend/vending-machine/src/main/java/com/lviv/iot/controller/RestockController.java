package com.lviv.iot.controller;

import com.lviv.iot.domain.Restock;
import com.lviv.iot.dto.RestockDto;
import com.lviv.iot.dto.assembler.RestockDtoAssembler;
import com.lviv.iot.service.RestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restocks")
public class RestockController {
    @Autowired
    private RestockService restockService;
    @Autowired
    private RestockDtoAssembler restockDtoAssembler;

    @GetMapping(value = "/{restockId}")
    public ResponseEntity<RestockDto> getRestock(@PathVariable Integer restockId) {
        Restock restock = restockService.findById(restockId);
        RestockDto restockDto = restockDtoAssembler.toModel(restock);
        return new ResponseEntity<>(restockDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RestockDto>> getAllRestocks() {
        List<Restock> restocks = restockService.findAll();
        CollectionModel<RestockDto> restockDtos = restockDtoAssembler.toCollectionModel(restocks);
        return new ResponseEntity<>(restockDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RestockDto> addRestock(@RequestBody Restock restock) {
        Restock newRestock = restockService.create(restock);
        RestockDto restockDto = restockDtoAssembler.toModel(newRestock);
        return new ResponseEntity<>(restockDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{restockId}")
    public ResponseEntity<?> updateRestock(@RequestBody Restock uRestock, @PathVariable Integer restockId) {
        restockService.update(restockId, uRestock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{restockId}")
    public ResponseEntity<?> deleteRestock(@PathVariable Integer restockId) {
        restockService.delete(restockId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}