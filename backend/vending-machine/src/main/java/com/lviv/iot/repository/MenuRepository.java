package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.Menu;
import com.lviv.iot.domain.MenuId;

@Repository
public interface MenuRepository extends JpaRepository<Menu, MenuId> {
}