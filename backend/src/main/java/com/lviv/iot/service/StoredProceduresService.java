package com.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.repository.CountryRepository;

@Service
public class StoredProceduresService {
    @Autowired
    CountryRepository countryRepository;

    public String insertIntoCashWithdrawal(String serviceMemberName, String serviceMemberLastName,
            String vendingMachineModelName, Integer amount) {
        return ("Newly inserted row id: " + countryRepository.insertIntoCashWithdrawal(serviceMemberName,
                serviceMemberLastName, vendingMachineModelName, amount));
    }

    public String getAvgCashWithdrawalAmount() {
        return ("Average money income: " + countryRepository.getAvgCashWithdrawalAmount());
    }
}