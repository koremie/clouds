package com.lviv.iot.controller;

import com.lviv.iot.domain.Trademark;
import com.lviv.iot.dto.TrademarkDto;
import com.lviv.iot.dto.assembler.TrademarkDtoAssembler;
import com.lviv.iot.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trademarks")
public class TrademarkController {
    @Autowired
    private TrademarkService trademarkService;
    @Autowired
    private TrademarkDtoAssembler trademarkDtoAssembler;

    @GetMapping(value = "/{trademarkId}")
    public ResponseEntity<TrademarkDto> getTrademark(@PathVariable String trademarkId) {
        Trademark trademark = trademarkService.findById(trademarkId);
        TrademarkDto  trademarkDto = trademarkDtoAssembler.toModel(trademark);
        return new ResponseEntity<>(trademarkDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TrademarkDto>> getAllTrademarks() {
        List<Trademark> trademarks = trademarkService.findAll();
        CollectionModel<TrademarkDto> trademarkDtos = trademarkDtoAssembler.toCollectionModel(trademarks);
        return new ResponseEntity<>(trademarkDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TrademarkDto> addTrademark(@RequestBody Trademark trademark) {
        Trademark newTrademark = trademarkService.create(trademark);
        TrademarkDto trademarkDto = trademarkDtoAssembler.toModel(newTrademark);
        return new ResponseEntity<>(trademarkDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{trademarkId}")
    public ResponseEntity<?> updateTrademark(@RequestBody Trademark uTrademark, @PathVariable String trademarkId) {
        trademarkService.update(trademarkId, uTrademark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{trademarkId}")
    public ResponseEntity<?> deleteTrademark(@PathVariable String trademarkId) {
        trademarkService.delete(trademarkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}