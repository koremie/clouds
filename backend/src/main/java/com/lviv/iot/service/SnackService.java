package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Snack;
import com.lviv.iot.exception.SnackNotFoundException;
import com.lviv.iot.repository.SnackRepository;

@Service
public class SnackService implements GeneralService<Snack, Integer> {
    @Autowired
    SnackRepository snackRepository;

    @Override
    public List<Snack> findAll() {
        return snackRepository.findAll();
    }

    @Override
    public Snack findById(Integer id) {
        return snackRepository.findById(id).orElseThrow(() -> new SnackNotFoundException(id));
    }

    @Override
    @Transactional
    public Snack create(Snack snack) {
        snackRepository.save(snack);
        return snack;
    }

    @Override
    @Transactional
    public void update(Integer id, Snack uSnack) {
        Snack snack = snackRepository.findById(id).orElseThrow(() -> new SnackNotFoundException(id));

        snack.setName(uSnack.getName());
        snack.setNetWeight(uSnack.getNetWeight());
        snack.setTrademark(uSnack.getTrademark());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Snack snack = snackRepository.findById(id).orElseThrow(() -> new SnackNotFoundException(id));

        snackRepository.delete(snack);
    }
}