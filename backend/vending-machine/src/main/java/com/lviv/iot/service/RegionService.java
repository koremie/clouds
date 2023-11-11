package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Region;
import com.lviv.iot.exception.CityNotFoundException;
import com.lviv.iot.exception.RegionNotFoundException;
import com.lviv.iot.repository.CountryRepository;
import com.lviv.iot.repository.RegionRepository;

@Service
public class RegionService implements GeneralService<Region, Integer> {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region findById(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new RegionNotFoundException(id));
    }

    @Override
    @Transactional
    public Region create(Region region) {
        regionRepository.save(region);
        return region;
    }

    @Override
    @Transactional
    public void update(Integer id, Region uRegion) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new RegionNotFoundException(id));

        region.setName(uRegion.getName());
        region.setCountry(countryRepository.findById(uRegion.getCountry().getId())
                .orElseThrow(() -> new CityNotFoundException(uRegion.getCountry().getId())));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new RegionNotFoundException(id));

        regionRepository.delete(region);
    }
}