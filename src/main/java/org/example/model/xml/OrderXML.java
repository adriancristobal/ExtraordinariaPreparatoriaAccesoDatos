package org.example.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import org.example.model.xml.adapters.LocalDateAdapter;
import org.example.model.xml.list.MenuItemsXML;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXML {

    private int table_id;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate order_date;
    private double total;
    private MenuItemsXML menuItems;
}
