package com.lviv.iot.controller;

import com.lviv.iot.domain.Snack;
import com.lviv.iot.dto.SnackDto;
import com.lviv.iot.dto.assembler.SnackDtoAssembler;
import com.lviv.iot.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/snacks")
public class SnackController {
    @Autowired
    private SnackService snackService;
    @Autowired
    private SnackDtoAssembler snackDtoAssembler;

    @GetMapping(value = "/{snackId}")
    public ResponseEntity<SnackDto> getSnack(@PathVariable Integer snackId) {
        Snack snack = snackService.findById(snackId);
        SnackDto snackDto = snackDtoAssembler.toModel(snack);
        return new ResponseEntity<>(snackDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<SnackDto>> getAllSnacks() {
        List<Snack> snacks = snackService.findAll();
        CollectionModel<SnackDto> snackDtos = snackDtoAssembler.toCollectionModel(snacks);
        return new ResponseEntity<>(snackDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SnackDto> addSnack(@RequestBody Snack snack) {
        Snack newSnack = snackService.create(snack);
        SnackDto snackDto = snackDtoAssembler.toModel(newSnack);
        return new ResponseEntity<>(snackDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{snackId}")
    public ResponseEntity<?> updateSnack(@RequestBody Snack uSnack, @PathVariable Integer snackId) {
        snackService.update(snackId, uSnack);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{snackId}")
    public ResponseEntity<?> deleteSnack(@PathVariable Integer snackId) {
        snackService.delete(snackId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}