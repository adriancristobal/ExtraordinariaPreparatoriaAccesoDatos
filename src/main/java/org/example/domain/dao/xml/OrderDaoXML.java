package org.example.domain.dao.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.config.file.ConfigProperties;
import org.example.model.xml.CustomerXML;
import org.example.model.xml.OrderXML;
import org.example.model.xml.list.CustomersXML;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OrderDaoXML {

    public List<OrderXML> getAll(String firstName) {
        List<OrderXML> listOrdersOfCustomer = null;

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
            List<CustomerXML> customersWithName = listCustomers.stream().filter(customerXML -> customerXML.getFirst_name().equals(firstName)).toList();
            if (customersWithName.size() > 0) {
                listOrdersOfCustomer = customersWithName.get(0).getOrder();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrdersOfCustomer;
    }

    public void add(String customerName, int table_id, LocalDate order_date, double total) {

        try {
            JAXBContext context = JAXBContext.newInstance(CustomersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile = Paths
                    .get(ConfigProperties.getInstance().getProperty("CustomerXMLFile"));

            OrderXML orderXML = new OrderXML();

            CustomersXML customersXML = (CustomersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            List<CustomerXML> listCustomers = new ArrayList<>();
            listCustomers.addAll(customersXML.getCustomers());

            CustomerXML customerXMLAntiguo = new CustomerXML();
            CustomerXML customerXMLNuevo = new CustomerXML();

            for (CustomerXML c : listCustomers) {
                if (c.getFirst_name().equals(customerName)) {
                    orderXML.setTable_id(table_id);
                    orderXML.setOrder_date(order_date);
                    orderXML.setTotal(total);
                    customerXMLAntiguo = c;

                    c.getOrder().add(orderXML);
                    customerXMLNuevo = c;
                }
            }

            try {
                customersXML.removeCustomer(customerXMLAntiguo);
                customersXML.addCustomer(customerXMLNuevo);
                marshaller.marshal(customersXML, Files.newOutputStream(xmlFile));
            }catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
