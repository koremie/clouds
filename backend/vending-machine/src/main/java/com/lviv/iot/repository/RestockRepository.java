package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.Restock;

@Repository
public interface RestockRepository extends JpaRepository<Restock, Integer> {
}