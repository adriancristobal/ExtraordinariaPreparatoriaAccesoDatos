package org.example.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.domain.service.CustomerService;
import org.example.domain.service.OrderService;
import org.example.model.MenuItemTXT;
import org.example.model.OrderItemTXT;
import org.example.model.OrderTXT;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        CustomerService customerService = container.select(CustomerService.class).get();
        OrderService orderService = container.select(OrderService.class).get();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Please choose an action:");
            System.out.println("1. View all customers");
            System.out.println("2. View all orders by customer");
            System.out.println("3. Add order with two items");
            System.out.println("4. Delete customer");
            System.out.println("5. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Customers: " + customerService.getAll());
                case 2 -> {
                    System.out.println("Please enter the customer id:");
                    int customerId = scanner.nextInt();
                    System.out.println("Orders by customer: " + orderService.getAll(customerId));
                }
                case 3 -> {
                    OrderTXT orderTXT = new OrderTXT(4, 1, 2, Date.valueOf("2021-05-01"), 43.0);
                    OrderItemTXT orderItemTXT1 = new OrderItemTXT(3, 1, 1, 2, 15.0);
                    OrderItemTXT orderItemTXT2 = new OrderItemTXT(4, 1, 2, 1, 28.0);
                    List<OrderItemTXT> list = List.of(orderItemTXT1, orderItemTXT2);
                    orderService.add(orderTXT, list);
                }
                case 4 -> {
                    System.out.println("Please enter the customer id:");
                    int customerId = scanner.nextInt();
                    int result = customerService.delete(customerId, false);
                    if (result == -1) {
                        System.out.println("Customer does not exist.");
                    } else if (result == 0) {
                        System.out.println("Customer has orders. Please confirm. (Y/N)");
                        String confirm = scanner.next();
                        if (confirm.equals("Y")) {
                            result = customerService.delete(customerId, true);
                            if (result == 1) {
                                System.out.println("Customer deleted.");
                            } else {
                                System.out.println("Customer not deleted.");
                            }
                        } else {
                            System.out.println("Customer not deleted.");
                        }
                    } else {
                        System.out.println("Customer deleted.");
                    }

                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
        container.close();
    }

}
