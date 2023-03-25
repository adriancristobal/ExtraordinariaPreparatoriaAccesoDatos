package org.example.domain.dao;

import org.example.config.file.ConfigProperties;
import org.example.model.CustomerTXT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
}
