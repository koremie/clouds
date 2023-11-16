package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.MachineModel;
import com.lviv.iot.exception.MachineModelNotFoundException;
import com.lviv.iot.repository.MachineModelRepository;

@Service
public class MachineModelService implements GeneralService<MachineModel, String> {
    @Autowired
    MachineModelRepository machineModelRepository;

    @Override
    public List<MachineModel> findAll() {
        return machineModelRepository.findAll();
    }

    @Override
    public MachineModel findById(String name) {
        return machineModelRepository.findById(name).orElseThrow(() -> new MachineModelNotFoundException(name));
    }

    @Override
    @Transactional
    public MachineModel create(MachineModel machineModel) {
        machineModelRepository.save(machineModel);
        return machineModel;
    }

    @Override
    @Transactional
    public void update(String name, MachineModel uMachineModel) {
        MachineModel machineModel = machineModelRepository.findById(name)
                .orElseThrow(() -> new MachineModelNotFoundException(name));
        machineModel.setName(uMachineModel.getName());
        machineModel.setCompany(uMachineModel.getCompany());
    }

    @Override
    @Transactional
    public void delete(String name) {
        MachineModel machineModel = machineModelRepository.findById(name)
                .orElseThrow(() -> new MachineModelNotFoundException(name));
        machineModelRepository.delete(machineModel);
    }
}