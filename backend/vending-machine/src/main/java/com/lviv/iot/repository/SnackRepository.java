package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.Snack;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Integer> {
}