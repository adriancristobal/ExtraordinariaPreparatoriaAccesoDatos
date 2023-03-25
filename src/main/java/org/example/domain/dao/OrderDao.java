package org.example.domain.dao;

import org.example.config.file.ConfigProperties;
import org.example.model.OrderTXT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao {

    public List<OrderTXT> getAll(int idCustomer) {
        File file = new File(ConfigProperties.getInstance().getProperty("OrderFile"));
        ArrayList<OrderTXT> listArticle = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                OrderTXT orderTXT = new OrderTXT(st);
                if (orderTXT.getCustomer_id() == idCustomer) {
                    listArticle.add(orderTXT);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }
}
