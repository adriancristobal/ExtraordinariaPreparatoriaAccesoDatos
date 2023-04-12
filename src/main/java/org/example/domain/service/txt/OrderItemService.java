package org.example.domain.service.txt;

import jakarta.inject.Inject;
import org.example.domain.dao.txt.MenuItemDao;
import org.example.domain.dao.txt.OrderItemDao;
import org.example.model.txt.MenuItemTXT;
import org.example.model.txt.OrderItemTXT;

import java.util.List;

public class OrderItemService {

    private final OrderItemDao dao;

    @Inject
    public OrderItemService(OrderItemDao dao) {
        this.dao = dao;
    }

    public List<OrderItemTXT> getAll() {
        return dao.getAll();
    }


}
