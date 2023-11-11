package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}