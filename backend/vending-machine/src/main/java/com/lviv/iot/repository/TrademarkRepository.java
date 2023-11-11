package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.Trademark;

@Repository
public interface TrademarkRepository extends JpaRepository<Trademark, String> {
    @Procedure("insert_into_trademark")
    String insertIntoTrademark(String name);
}