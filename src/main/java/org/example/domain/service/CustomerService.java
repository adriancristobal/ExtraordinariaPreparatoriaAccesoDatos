package org.example.domain.service;

import jakarta.inject.Inject;
import org.example.domain.dao.CustomerDao;
import org.example.model.CustomerTXT;

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
}
