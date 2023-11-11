package com.lviv.iot.controller;

import com.lviv.iot.domain.Region;
import com.lviv.iot.dto.RegionDto;
import com.lviv.iot.dto.assembler.RegionDtoAssembler;
import com.lviv.iot.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/regions")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionDtoAssembler regionDtoAssembler;

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<RegionDto> getRegion(@PathVariable Integer regionId) {
        Region region = regionService.findById(regionId);
        RegionDto regionDto = regionDtoAssembler.toModel(region);
        return new ResponseEntity<>(regionDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RegionDto>> getAllRegions() {
        List<Region> regions = regionService.findAll();
        CollectionModel<RegionDto> regionDtos = regionDtoAssembler.toCollectionModel(regions);
        return new ResponseEntity<>(regionDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RegionDto> addRegion(@RequestBody Region region) {
        Region newRegion = regionService.create(region);
        RegionDto regionDto = regionDtoAssembler.toModel(newRegion);
        return new ResponseEntity<>(regionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{regionId}")
    public ResponseEntity<?> updateRegion(@RequestBody Region uRegion, @PathVariable Integer regionId) {
        regionService.update(regionId, uRegion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{regionId}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer regionId) {
        regionService.delete(regionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}