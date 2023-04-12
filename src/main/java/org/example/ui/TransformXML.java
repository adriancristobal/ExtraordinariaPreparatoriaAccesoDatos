package org.example.ui;

import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.domain.service.txt.CustomerService;
import org.example.domain.service.txt.MenuItemService;
import org.example.domain.service.txt.OrderItemService;
import org.example.domain.service.txt.OrderService;
import org.example.model.txt.CustomerTXT;
import org.example.model.txt.MenuItemTXT;
import org.example.model.txt.OrderTXT;
import org.example.model.xml.CustomerXML;
import org.example.model.xml.MenuItemXML;
import org.example.model.xml.OrderXML;
import org.example.model.xml.list.MenuItemsXML;


import java.util.ArrayList;
import java.util.List;

public class TransformXML {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        CustomerService customerService = container.select(CustomerService.class).get();
        MenuItemService menuItemService = container.select(MenuItemService.class).get();
        OrderService orderService = container.select(OrderService.class).get();
        OrderItemService orderItemService = container.select(OrderItemService.class).get();

        List<CustomerTXT> customerList = customerService.getAll();
        List<OrderTXT> orderList = orderService.getAll();
        List<MenuItemTXT> menuItemList = menuItemService.getAll();
        //Either<Integer, List<Client>> listC = clientsDAOdb.getAll();


        List<CustomerXML> customers = new ArrayList<>();


        for (CustomerTXT c :
                customerList) {

            List<OrderTXT> orders = orderList.stream()
                    .filter(order -> order.getCustomer_id() == c.getId()).toList();

            for (OrderTXT order :
                    orders) {

                MenuItemsXML menuItemsXML = new MenuItemsXML(menuItemList.stream()
                        .filter(menuItem -> menuItem.getId() == order.ge())
                        .map(orderItem -> new MenuItemXML(menuItemList.stream()
                                .filter(menuItem -> menuItem.getId() == orderItem.getMenu_item_id())
                                .map(menuItem -> menuItem.getId())
                                .toList()))
                        .toList());
            }





            System.out.println(orders);
            // i mapped the list of purchases with the items of every client
            CustomerXML customerXML = new CustomerXML(c.getFirst_name(), c.getLast_name(), c.getEmail(), c.getPhone(), orders);
            customers.add(customerXML);
        }
        CustomersXML customersXML= new CustomersXML(customers);
        int code= clientsDAOxml.add(clientsXml);
        System.out.println(code);
        String message;
        if (code != 0) {
            message = switch (code) {
                case NumericConstants.NON_RELATED_TO_DB_EXCEPTION_CODE ->
                        "ERROR JAXB connection";
                default -> "ERROR JAXB writing";
            };
        } else {
            message = "Clients backup done!";
        }
        System.out.println(message);


//        for (CustomerTXT c :
//                customerList) {
//            OrdersXML orders = new OrdersXML(orderList.stream()
//                    .filter(order -> order.getCustomer_id() == c.getId())
//                    .map(order -> new OrderXML(new MenuItemsXML(menuItemList.stream()
//                            .filter(menuItem -> menuItem.getId() == order.g)
//                            .map(purchases_items -> new ItemXML(purchases_items.getItem().getId()))
//                            .toList()), purchase.getTotal_cost()))
//                    .toList());
//
//            System.out.println(orders);
//            // i mapped the list of purchases with the items of every client
//            CustomerXML customerXML = new CustomerXML(c.getFirst_name(), c.getLast_name(), c.getEmail(), c.getPhone(), orders);
//            customers.add(customerXML);
//        }
//        CustomersXML customersXML= new CustomersXML(customers);
//        int code= clientsDAOxml.add(clientsXml);
//        System.out.println(code);
//        String message;
//        if (code != 0) {
//            message = switch (code) {
//                case NumericConstants.NON_RELATED_TO_DB_EXCEPTION_CODE ->
//                        "ERROR JAXB connection";
//                default -> "ERROR JAXB writing";
//            };
//        } else {
//            message = "Clients backup done!";
//        }
//        System.out.println(message);



    }
}
