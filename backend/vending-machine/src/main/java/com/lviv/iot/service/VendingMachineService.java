package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Menu;
import com.lviv.iot.domain.VendingMachine;
import com.lviv.iot.exception.VendingMachineNotFoundException;
import com.lviv.iot.repository.VendingMachineRepository;

@Service
public class VendingMachineService implements GeneralService<VendingMachine, Integer> {
    @Autowired
    VendingMachineRepository vendingMachineRepository;

    @Override
    public List<VendingMachine> findAll() {
        return vendingMachineRepository.findAll();
    }

    @Override
    public VendingMachine findById(Integer id) {
        return vendingMachineRepository.findById(id).orElseThrow(() -> new VendingMachineNotFoundException(id));
    }
    
    public List<Menu> findMenuById(Integer id) {
        VendingMachine vendingMachine = vendingMachineRepository.findById(id)
                .orElseThrow(() -> new VendingMachineNotFoundException(id));
        return vendingMachine.getMenus().stream().toList();
    }

    @Override
    @Transactional
    public VendingMachine create(VendingMachine vendingMachine) {
        vendingMachineRepository.save(vendingMachine);
        return vendingMachine;
    }

    @Override
    @Transactional
    public void update(Integer id, VendingMachine uVendingMachine) {
        VendingMachine vendingMachine = vendingMachineRepository.findById(id)
                .orElseThrow(() -> new VendingMachineNotFoundException(id));

        vendingMachine.setGps(uVendingMachine.getGps());
        vendingMachine.setContactNumber(uVendingMachine.getContactNumber());
        vendingMachine.setMachineModel(uVendingMachine.getMachineModel());
        vendingMachine.setAddress(uVendingMachine.getAddress());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        VendingMachine vendingMachine = vendingMachineRepository.findById(id)
                .orElseThrow(() -> new VendingMachineNotFoundException(id));

        vendingMachineRepository.delete(vendingMachine);
    }
}