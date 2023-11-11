package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.VendingMachine;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Integer> {
}