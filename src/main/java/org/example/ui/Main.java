package org.example.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.domain.service.CustomerService;
import org.example.domain.service.OrderService;

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
            System.out.println("3. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Customers: " + customerService.getAll());
                case 2 -> {
                    System.out.println("Please enter the customer id:");
                    int customerId = scanner.nextInt();
                    System.out.println("Orders by customer: " + orderService.getAll(customerId));
                }
                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
        container.close();
    }

}
