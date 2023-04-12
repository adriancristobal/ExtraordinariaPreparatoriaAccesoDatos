package org.example.model.xml.list;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.model.xml.CustomerXML;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersXML {
    @XmlElement(name = "customer")
    private List<CustomerXML> customers;

    public CustomersXML(List<CustomerXML> customers) {
        this.customers = customers;
    }

    public List<CustomerXML> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerXML> listCustomers) {
        this.customers = listCustomers;
    }

    public void addCustomer(CustomerXML c) {
        customers.add(c);
    }

    public void removeCustomer(CustomerXML c) {
        customers.remove(c);
    }

    @Override
    public String toString() {
        return "Customers{" + "Customer=" + customers + '}' + '\n';
    }
}
