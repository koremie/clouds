package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lviv.iot.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
}