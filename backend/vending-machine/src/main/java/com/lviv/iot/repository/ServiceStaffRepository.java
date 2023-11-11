package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.ServiceStaff;

@Repository
public interface ServiceStaffRepository extends JpaRepository<ServiceStaff, Integer> {
}