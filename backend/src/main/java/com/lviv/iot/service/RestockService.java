package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Restock;
import com.lviv.iot.exception.RestockNotFoundException;
import com.lviv.iot.repository.RestockRepository;

@Service
public class RestockService implements GeneralService<Restock, Integer> {
    @Autowired
    RestockRepository restockRepository;

    @Override
    public List<Restock> findAll() {
        return restockRepository.findAll();
    }

    @Override
    public Restock findById(Integer id) {
        return restockRepository.findById(id).orElseThrow(() -> new RestockNotFoundException(id));
    }

    @Override
    @Transactional
    public Restock create(Restock restock) {
        restockRepository.save(restock);
        return restock;
    }

    @Override
    @Transactional
    public void update(Integer id, Restock uRestock) {
        Restock restock = restockRepository.findById(id).orElseThrow(() -> new RestockNotFoundException(id));

        restock.setQuantity(uRestock.getQuantity());
        restock.setDate(uRestock.getDate());
        restock.setMenu(uRestock.getMenu());
        restock.setServiceStaff(uRestock.getServiceStaff());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Restock restock = restockRepository.findById(id).orElseThrow(() -> new RestockNotFoundException(id));

        restockRepository.delete(restock);
    }
}