package org.example.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.example.model.xml.list.MenuItemsXML;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXML {

    private int table_id;
    private Date order_date;
    private double total;
    private MenuItemsXML menuItems;
}
