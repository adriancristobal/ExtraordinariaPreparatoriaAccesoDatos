package org.example.domain.service;

import jakarta.inject.Inject;
import org.example.domain.dao.CustomerDao;
import org.example.domain.dao.OrderDao;
import org.example.model.CustomerTXT;
import org.example.model.OrderTXT;

import java.util.List;

public class OrderService {

    private final OrderDao dao;

    @Inject
    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public List<OrderTXT> getAll(int idCustomer) {
        return dao.getAll(idCustomer);
    }
}
