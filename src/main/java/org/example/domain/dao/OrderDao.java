package org.example.domain.dao;

import io.vavr.control.Either;
import org.example.config.file.ConfigProperties;
import org.example.model.CustomerTXT;
import org.example.model.MenuItemTXT;
import org.example.model.OrderItemTXT;
import org.example.model.OrderTXT;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao {

    public List<OrderTXT>  getAll() {
        File file = new File(ConfigProperties.getInstance().getProperty("OrderFile"));
        ArrayList<OrderTXT> listArticle = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                OrderTXT orderTXT = new OrderTXT(st);
                listArticle.add(orderTXT);
            }
        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }

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

    public boolean delete(OrderTXT orderTXT) {
        List<OrderTXT> listOrder = getAll();
        File file = new File(ConfigProperties.getInstance().getProperty("OrderFile"));
        try (FileWriter writer = new FileWriter(file, false);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("");
            listOrder.remove(orderTXT);
            listOrder.forEach(this::add);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Either<Integer, String> add(OrderTXT order, List<OrderItemTXT> items) {
        // Check that customer exists
        CustomerDao customerDao = new CustomerDao();
        CustomerTXT customer = customerDao.get(order.getCustomer_id());
        customerDao.get(order.getCustomer_id());
        if (customer == null) {
            System.out.println("Customer with ID " + order.getCustomer_id() + " does not exist.");
            return Either.left(1);
        }

        // Check that menu items exist
        MenuItemDao menuItemDao = new MenuItemDao();
        for (OrderItemTXT item : items) {
            MenuItemTXT menuItem = menuItemDao.get(item.getMenu_item_id());
            if (menuItem == null) {
                System.out.println("Menu item with ID " + item.getMenu_item_id() + " does not exist.");
                return Either.left(2);
            }
        }

        // Append order and items to file
        File file = new File(ConfigProperties.getInstance().getProperty("OrderFile"));
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            // Write order
            String orderContent = order.getId() + ";" + order.getTable_id() + ";" + order.getCustomer_id()
                    + ";" + order.getOrder_date() + ";" + order.getTotal() + "\n";
            bw.write(orderContent);

            // Write items
            for (OrderItemTXT item : items) {
                String itemContent = item.getId() + ";" + item.getOrder_id() + ";" + item.getMenu_item_id()
                        + ";" + item.getQuantity() + ";" + item.getPrice() + "\n";
                bw.write(itemContent);
            }

            return Either.right(orderContent);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return Either.left(3);
        }
    }

    public boolean add(OrderTXT order) {
        File file = new File(ConfigProperties.getInstance().getProperty("OrderFile"));
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            String orderContent = order.getId() + ";" + order.getTable_id() + ";" + order.getCustomer_id()
                    + ";" + order.getOrder_date() + ";" + order.getTotal() + "\n";
            bw.write(orderContent);
            return true;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return false;
        }
    }

}
