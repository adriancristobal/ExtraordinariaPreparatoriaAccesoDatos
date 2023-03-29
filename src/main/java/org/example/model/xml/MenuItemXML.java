package org.example.model.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItemXML {

    private String name;
    private double price;
    private int quantity;

}
