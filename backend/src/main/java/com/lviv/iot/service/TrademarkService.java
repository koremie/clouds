package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Trademark;
import com.lviv.iot.exception.TrademarkNotFoundException;
import com.lviv.iot.repository.TrademarkRepository;

@Service
public class TrademarkService implements GeneralService<Trademark, String> {
    @Autowired
    TrademarkRepository trademarkRepository;

    @Override
    public List<Trademark> findAll() {
        return trademarkRepository.findAll();
    }

    @Override
    public Trademark findById(String name) {
        return trademarkRepository.findById(name).orElseThrow(() -> new TrademarkNotFoundException(name));
    }

    @Override
    @Transactional
    public Trademark create(Trademark trademark) {
        return new Trademark() {
            {
                setName(trademarkRepository.insertIntoTrademark(trademark.getName()));
            }
        };
    }

    @Override
    @Transactional
    public void update(String name, Trademark uTrademark) {
        Trademark trademark = trademarkRepository.findById(name)
                .orElseThrow(() -> new TrademarkNotFoundException(name));
        trademark.setName(uTrademark.getName());
    }

    @Override
    @Transactional
    public void delete(String name) {
        Trademark trademark = trademarkRepository.findById(name)
                .orElseThrow(() -> new TrademarkNotFoundException(name));
        trademarkRepository.delete(trademark);
    }
}