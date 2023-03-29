package org.example.domain.service;

import jakarta.inject.Inject;
import org.example.domain.dao.txt.CustomerDao;
import org.example.model.txt.CustomerTXT;

import java.util.List;

public class CustomerService {

    private final CustomerDao dao;

    @Inject
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public List<CustomerTXT> getAll() {
        return dao.getAll();
    }

    public boolean add(CustomerTXT customer) {
        return dao.add(customer);
    }

    public int delete(int id, boolean confirm) {
        int result = dao.delete(id, confirm);
        return result;
    }
}
