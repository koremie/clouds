package com.lviv.iot.controller;

import com.lviv.iot.dto.CashWithdrawalDto;
import com.lviv.iot.service.StoredProceduresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/stored-procedures")
public class StoredProceduresController {
    @Autowired
    private StoredProceduresService storedProceduresService;

    @PostMapping(value = "/insert-into/cash-withdrawal")
    public ResponseEntity<String> insertIntoCashWithdrawal(@RequestBody CashWithdrawalDto cashWithdrawalDto) {
        return new ResponseEntity<>(storedProceduresService.insertIntoCashWithdrawal(
                cashWithdrawalDto.getServiceMemberName(), cashWithdrawalDto.getServiceMemberLastName(),
                cashWithdrawalDto.getVendingMachineModelName(), cashWithdrawalDto.getAmount()), HttpStatus.OK);
    }

    @GetMapping(value = "/cash-withdrawal/average/amount")
    public ResponseEntity<String> getAvgCashWithdrawalAmount() {
        return new ResponseEntity<>(storedProceduresService.getAvgCashWithdrawalAmount(), HttpStatus.OK);
    }
}