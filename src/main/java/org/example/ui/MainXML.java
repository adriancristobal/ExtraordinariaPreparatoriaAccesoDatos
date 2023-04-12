package org.example.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.domain.service.txt.CustomerService;
import org.example.domain.service.txt.OrderService;
import org.example.domain.service.xml.CustomerServiceXML;
import org.example.domain.service.xml.OrderServiceXML;
import org.example.model.txt.OrderItemTXT;
import org.example.model.txt.OrderTXT;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainXML {

    public static void main(String[] args) {

    SeContainerInitializer initializer = SeContainerInitializer.newInstance();
    final SeContainer container = initializer.initialize();

    CustomerServiceXML customerService = container.select(CustomerServiceXML.class).get();
    OrderServiceXML orderService = container.select(OrderServiceXML.class).get();

    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println("Please choose an action:");
        System.out.println("1. Get information about the orders of a given customer");
        System.out.println("2. Get the customers that have ordered a given item of the menu");
        System.out.println("3. Append a new order to a given customer");
        System.out.println("4. Delete a customer with all the orders");
        System.out.println("5. Exit");

        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Please enter the customer first name:");
                String firstName = scanner.next();
                System.out.println("Orders of customer: " + orderService.getAll(firstName));
            }
            case 2 -> {
                //Get the customers that have ordered a given item of the menu
                System.out.println("Please enter the item name:");
                String itemName = scanner.next();
                System.out.println("Customers that have ordered " + itemName + ": " + customerService.getAll(itemName));
            }
            case 3 -> {
                //Append a new order to a given customer
                System.out.println("Please enter the customer first name:");
                String firstName = scanner.next();
                System.out.println("Please enter the table id:");
                int table_id = scanner.nextInt();
                LocalDate order_date = LocalDate.now();
                System.out.println("Please enter the total:");
                double total = scanner.nextDouble();
                orderService.add(firstName, table_id, order_date, total);
            }
            case 4 -> {
                System.out.println("Please enter the customer name:");
                String customerName = scanner.next();
                String result = customerService.delete(customerName);
                System.out.println(result);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    } while (choice != 3);

        scanner.close();
        container.close();
    }

}
