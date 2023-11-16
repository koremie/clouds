package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.City;
import com.lviv.iot.exception.CityNotFoundException;
import com.lviv.iot.repository.CityRepository;
import com.lviv.iot.repository.RegionRepository;

@Service
public class CityService implements GeneralService<City, Integer> {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    RegionRepository regionRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    @Override
    @Transactional
    public City create(City city) {
        cityRepository.save(city);
        return city;
    }

    @Override
    @Transactional
    public void update(Integer id, City uCity) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));

        city.setName(uCity.getName());
        city.setRegion(regionRepository.findById(uCity.getRegion().getId())
                .orElseThrow(() -> new CityNotFoundException(uCity.getRegion().getId())));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));

        cityRepository.delete(city);
    }
}