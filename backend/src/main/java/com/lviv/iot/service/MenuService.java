package com.lviv.iot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.iot.domain.Menu;
import com.lviv.iot.domain.MenuId;
import com.lviv.iot.exception.MenuNotFoundException;
import com.lviv.iot.repository.MenuRepository;

@Service
public class MenuService implements GeneralService<Menu, MenuId> {
    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(MenuId id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(id.getSlotNumber(), id.getVendingMachine()));
    }

    @Override
    @Transactional
    public Menu create(Menu menu) {
        menuRepository.save(menu);
        return menu;
    }

    @Override
    @Transactional
    public void update(MenuId id, Menu uMenu) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(id.getSlotNumber(), id.getVendingMachine()));

        menu.setQuantity(uMenu.getQuantity());
        menu.setPricePerUnit(uMenu.getPricePerUnit());
        menu.setSnack(uMenu.getSnack());
    }

    @Override
    @Transactional
    public void delete(MenuId id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(id.getSlotNumber(), id.getVendingMachine()));

        menuRepository.delete(menu);
    }
}