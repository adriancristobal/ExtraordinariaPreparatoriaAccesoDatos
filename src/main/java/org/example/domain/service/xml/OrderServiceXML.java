package org.example.domain.service.xml;

import jakarta.inject.Inject;
import org.example.domain.dao.xml.CustomerDaoXML;
import org.example.domain.dao.xml.OrderDaoXML;
import org.example.model.xml.OrderXML;
import org.example.model.xml.list.CustomersXML;

import java.time.LocalDate;
import java.util.List;

public class OrderServiceXML {

    private final OrderDaoXML dao;

    @Inject
    public OrderServiceXML(OrderDaoXML dao) {
        this.dao = dao;
    }

    public List<OrderXML> getAll(String firstName){
        return dao.getAll(firstName);
    }

    public void add(String customerName, int table_id, LocalDate order_date, double total){
        dao.add(customerName, table_id, order_date, total);
    }
}
