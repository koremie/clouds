package com.lviv.iot.controller;

import com.lviv.iot.domain.MachineModel;
import com.lviv.iot.dto.MachineModelDto;
import com.lviv.iot.dto.assembler.MachineModelDtoAssembler;
import com.lviv.iot.service.MachineModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/machine-models")
public class MachineModelController {
    @Autowired
    private MachineModelService machineModelService;
    @Autowired
    private MachineModelDtoAssembler machineModelDtoAssembler;

    @GetMapping(value = "/{machineModelId}")
    public ResponseEntity<MachineModelDto> getMachineModel(@PathVariable String machineModelId) {
        MachineModel machineModel = machineModelService.findById(machineModelId);
        MachineModelDto machineModelDto = machineModelDtoAssembler.toModel(machineModel);
        return new ResponseEntity<>(machineModelDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<MachineModelDto>> getAllMachineModels() {
        List<MachineModel> machineModels = machineModelService.findAll();
        CollectionModel<MachineModelDto> machineModelDtos = machineModelDtoAssembler.toCollectionModel(machineModels);
        return new ResponseEntity<>(machineModelDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<MachineModelDto> addMachineModel(@RequestBody MachineModel machineModel) {
        MachineModel newMachineModel = machineModelService.create(machineModel);
        MachineModelDto machineModelDto = machineModelDtoAssembler.toModel(newMachineModel);
        return new ResponseEntity<>(machineModelDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{machineModelId}")
    public ResponseEntity<?> updateMachineModel(@RequestBody MachineModel uMachineModel, @PathVariable String machineModelId) {
        machineModelService.update(machineModelId, uMachineModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{machineModelId}")
    public ResponseEntity<?> deleteMachineModel(@PathVariable String machineModelId) {
        machineModelService.delete(machineModelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}