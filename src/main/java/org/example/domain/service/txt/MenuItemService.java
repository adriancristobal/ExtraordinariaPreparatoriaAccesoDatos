package org.example.domain.service.txt;

import jakarta.inject.Inject;
import org.example.domain.dao.txt.CustomerDao;
import org.example.domain.dao.txt.MenuItemDao;
import org.example.model.txt.CustomerTXT;
import org.example.model.txt.MenuItemTXT;

import java.util.List;

public class MenuItemService {

    private final MenuItemDao dao;

    @Inject
    public MenuItemService(MenuItemDao dao) {
        this.dao = dao;
    }

    public List<MenuItemTXT> getAll() {
        return dao.getAll();
    }
}
