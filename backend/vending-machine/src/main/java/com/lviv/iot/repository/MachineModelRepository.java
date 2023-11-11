package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lviv.iot.domain.MachineModel;

@Repository
public interface MachineModelRepository extends JpaRepository<MachineModel, String>{
}