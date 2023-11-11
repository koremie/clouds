package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Address;
import com.lviv.iot.exception.AddressNotFoundException;
import com.lviv.iot.exception.CityNotFoundException;
import com.lviv.iot.repository.AddressRepository;
import com.lviv.iot.repository.CityRepository;

@Service
public class AddressService implements GeneralService<Address, Integer> {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Integer id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    @Transactional
    public Address create(Address address) {
        addressRepository.save(address);
        return address;
    }

    @Override
    @Transactional
    public void update(Integer id, Address uAddress) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));

        address.setStreet(uAddress.getStreet());
        address.setHouseNumber(uAddress.getHouseNumber());
        address.setAppartmentNumber(uAddress.getAppartmentNumber());
        address.setCity(cityRepository.findById(uAddress.getCity().getId())
                .orElseThrow(() -> new CityNotFoundException(uAddress.getCity().getId())));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));

        addressRepository.delete(address);
    }
}