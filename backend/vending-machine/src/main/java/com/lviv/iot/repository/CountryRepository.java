package com.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.lviv.iot.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Procedure("insert_nonames_into_country")
    void insertNonamesIntoCountry();

    @Procedure("create_tables_by_country_names")
    void createTablesByCountryNames();
    
    @Procedure("insert_into_cash_withdrawal")
    Integer insertIntoCashWithdrawal(String serviceMemberName, String serviceMemberLastName,
            String vendingMachineModelName, Integer amount);

    @Procedure("get_avg_cash_withdrawal_amount")
    Double getAvgCashWithdrawalAmount();
}