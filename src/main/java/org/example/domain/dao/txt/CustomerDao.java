package org.example.domain.dao.txt;

import org.example.config.file.ConfigProperties;
import org.example.model.txt.CustomerTXT;
import org.example.model.txt.OrderTXT;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDao {

    public List<CustomerTXT> getAll() {
        File file = new File(ConfigProperties.getInstance().getProperty("CustomerFile"));
        ArrayList<CustomerTXT> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                CustomerTXT customerTXT = new CustomerTXT(st);
                list.add(customerTXT);
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public CustomerTXT get(int id) {
        File file = new File(ConfigProperties.getInstance().getProperty("CustomerFile"));
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                CustomerTXT customer = new CustomerTXT(st);
                if (customer.getId() == id) {
                    return customer;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int delete(int id, boolean confirm) {
        int result = -1;
        // Check if customer exists
        CustomerTXT customer = get(id);
        if (customer == null) {
            return -1;
        }

        // Check if customer has orders
        OrderDao orderDao = new OrderDao();
        List<OrderTXT> orders = orderDao.getAll(id);
        if (!orders.isEmpty()) {
            if (!confirm) {
                return 0;
            }

            // Delete all orders of the customer
            for (OrderTXT order : orders) {
                orderDao.delete(order);
            }
        }

        // Delete customer
        List<CustomerTXT> listCustomer = getAll();
        File file = new File(ConfigProperties.getInstance().getProperty("CustomerFile"));
        try (FileWriter writer = new FileWriter(file, false);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("");
            listCustomer.remove(customer);
            listCustomer.forEach(this::add);
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean add(CustomerTXT customer) {
        File file = new File(ConfigProperties.getInstance().getProperty("CustomerFile"));
        try (FileWriter writer = new FileWriter(file, true);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            String content = customer.getId() + ";" + customer.getFirst_name()
                    + ";" + customer.getLast_name() + ";" + customer.getEmail() + ";" + customer.getPhone() + "\n";
            bw.write(content);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
