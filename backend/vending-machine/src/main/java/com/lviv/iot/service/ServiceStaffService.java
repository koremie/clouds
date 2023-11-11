package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.ServiceStaff;
import com.lviv.iot.exception.ServiceStaffNotFoundException;
import com.lviv.iot.repository.ServiceStaffRepository;

@Service
public class ServiceStaffService implements GeneralService<ServiceStaff, Integer> {
    @Autowired
    ServiceStaffRepository serviceStaffRepository;

    @Override
    public List<ServiceStaff> findAll() {
        return serviceStaffRepository.findAll();
    }

    @Override
    public ServiceStaff findById(Integer id) {
        return serviceStaffRepository.findById(id).orElseThrow(() -> new ServiceStaffNotFoundException(id));
    }

    @Override
    @Transactional
    public ServiceStaff create(ServiceStaff serviceStaff) {
        serviceStaffRepository.save(serviceStaff);
        return serviceStaff;
    }

    @Override
    @Transactional
    public void update(Integer id, ServiceStaff uServiceStaff) {
        ServiceStaff serviceStaff = serviceStaffRepository.findById(id).orElseThrow(() -> new ServiceStaffNotFoundException(id));

        serviceStaff.setName(uServiceStaff.getName());
        serviceStaff.setLastname(uServiceStaff.getLastname());
        serviceStaff.setAddress(uServiceStaff.getAddress());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ServiceStaff city = serviceStaffRepository.findById(id).orElseThrow(() -> new ServiceStaffNotFoundException(id));

        serviceStaffRepository.delete(city);
    }
}