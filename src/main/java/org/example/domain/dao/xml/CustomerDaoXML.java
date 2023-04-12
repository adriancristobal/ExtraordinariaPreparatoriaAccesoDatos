package org.example.domain.dao.xml;


import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.config.file.ConfigProperties;
import org.example.model.txt.MenuItemTXT;
import org.example.model.xml.CustomerXML;
import org.example.model.xml.MenuItemXML;
import org.example.model.xml.OrderXML;
import org.example.model.xml.list.CustomersXML;
import org.example.model.xml.list.MenuItemsXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CustomerDaoXML {

    public CustomersXML getAll() {
        CustomersXML listCustomers = null;

        try {
            JAXBContext context = JAXBContext.newInstance(CustomersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(ConfigProperties.getInstance().getProperty("CustomerXMLFile"));

            // Read the XML document from the file
            listCustomers = (CustomersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCustomers;
    }

    public CustomersXML getAll(String itemName) {
        CustomersXML listCustomers = null;

        try {
            JAXBContext context = JAXBContext.newInstance(CustomersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(ConfigProperties.getInstance().getProperty("CustomerXMLFile"));

            // Read the XML document from the file
            List<CustomerXML> filterListCustomers = new ArrayList<>();
            listCustomers = (CustomersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            for (CustomerXML customerXML : listCustomers.getCustomers()) {
                List<OrderXML> orderXMLList = customerXML.getOrder();
                for (OrderXML orderXML : orderXMLList) {
                    MenuItemsXML menuItemsXMLList = orderXML.getMenuItems();
                    List<MenuItemXML> menuItemXMLList = menuItemsXMLList.getMenuItems();
                    for (MenuItemXML menuItemXML : menuItemXMLList) {
                        if (menuItemXML.getName().equals(itemName)) {
                            filterListCustomers.add(customerXML);
                        }
                    }
                }

            }
            listCustomers.setCustomers(filterListCustomers);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCustomers;
    }


    public int delete(String customerName) {
        int result = 0;

        try {
            JAXBContext context = JAXBContext.newInstance(CustomersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(ConfigProperties.getInstance().getProperty("CustomerXMLFile"));

            // Read the XML document from the file
            CustomersXML customerList = (CustomersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            List<CustomerXML> listCustomers = customerList.getCustomers();
            for (CustomerXML customerXML : listCustomers) {
                if (customerXML.getFirst_name().equals(customerName)) {
                    listCustomers.remove(customerXML);
                    break;
                }
            }
            customerList.setCustomers(listCustomers);
            marshaller.marshal(customerList, Files.newOutputStream(xmlFile));
            result = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int add(CustomersXML customersXML){
        JAXBContext context;
        Marshaller marshaller;
        try {
            context = JAXBContext.newInstance(CustomersXML.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            return -2;
        }
        Path xmlFile = Paths
                .get(ConfigProperties.getInstance().getProperty("CustomerXMLFile"));
        try {
            marshaller.marshal(customersXML, Files.newOutputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            return -1;
        }
        return 0;
    }
}
