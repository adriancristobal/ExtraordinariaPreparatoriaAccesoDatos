package org.example.domain.service.xml;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.example.domain.dao.txt.CustomerDao;
import org.example.domain.dao.xml.CustomerDaoXML;
import org.example.model.txt.CustomerTXT;
import org.example.model.xml.CustomerXML;
import org.example.model.xml.list.CustomersXML;

import java.util.List;

public class CustomerServiceXML {

    private final CustomerDaoXML dao;

    @Inject
    public CustomerServiceXML(CustomerDaoXML dao) {
        this.dao = dao;
    }

    public CustomersXML getAll(){
        return dao.getAll();
    }

    public CustomersXML getAll(String itemName){
        return dao.getAll(itemName);
    }

    public String delete(String customerName){
        int result = dao.delete(customerName);
        if (result == 1) {
            return "Customer deleted";
        } else if (result == 0) {
            return "Customer not found";
        } else {
            return "Error";
        }
    }

}
