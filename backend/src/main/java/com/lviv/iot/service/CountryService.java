package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Country;
import com.lviv.iot.exception.CountryNotFoundException;
import com.lviv.iot.repository.CountryRepository;

@Service
public class CountryService implements GeneralService<Country, Integer> {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        countryRepository.save(country);
        return country;
    }

    @Override
    @Transactional
    public void update(Integer id, Country uCountry) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(uCountry.getName());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        countryRepository.delete(country);
    }
    
    public void insertNonamesIntoCountry() {
        countryRepository.insertNonamesIntoCountry();
    }

    public void createTablesByCountryNames() {
        countryRepository.createTablesByCountryNames();
    }
}