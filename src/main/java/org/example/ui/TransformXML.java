package org.example.ui;

import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.domain.service.CustomerService;
import org.example.domain.service.MenuItemService;
import org.example.domain.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class TransformXML {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        CustomerService customerService = container.select(CustomerService.class).get();
        MenuItemService menuItemService = container.select(MenuItemService.class).get();
        OrderService orderService = container.select(OrderService.class).get();
        ClientsDAO clientsDAOxml = container.select(ClientsDAOxml.class).get();

        Either<Integer, List<Purchases_items>> listI = purchasesItemsDAOImpl.getAll();
        Either<Integer, List<Purchase>> listP = purchasesDAOdb.getAll(6);
        Either<Integer, List<Client>> listC = clientsDAOdb.getAll();
        if (listC.isRight() && listI.isRight() && listP.isRight()){
            List<ClientXML> clients = new ArrayList<>();



            for (Client c :
                    listC.get()) {
                // i have to filter by purchase id client
                PurchasesXML purchases = new PurchasesXML(listP.get().stream()
                        .filter(purchase -> purchase.getClient().getId() == c.getId())
                        .map(purchase -> new PurchaseXML(new ItemsXML(listI.get().stream()
                                .filter(purchases_items -> purchases_items.getPurchase().getId() == purchase.getId())
                                .map(purchases_items -> new ItemXML(purchases_items.getItem().getId()))
                                .toList()), purchase.getTotal_cost()))
                        .toList());

                System.out.println(purchases);
                // i mapped the list of purchases with the items of every client
                ClientXML clientXML = new ClientXML(c.getId(), c.getName(), c.getBalance(), purchases);
                clients.add(clientXML);
            }
            ClientsXML clientsXml= new ClientsXML(clients);
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
        }
    }
}
