package org.example.domain.dao.txt;

import org.example.config.file.ConfigProperties;
import org.example.model.txt.MenuItemTXT;
import org.example.model.txt.OrderItemTXT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderItemDao {

    public List<OrderItemTXT> getAll() {
        File file = new File(ConfigProperties.getInstance().getProperty("OrderItemFile"));
        ArrayList<OrderItemTXT> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                OrderItemTXT orderItemTXT = new OrderItemTXT(st);
                list.add(orderItemTXT);
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
