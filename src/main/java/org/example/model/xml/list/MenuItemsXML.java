package org.example.model.xml.list;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.model.xml.MenuItemXML;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "menuItems")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItemsXML {

    @XmlElement(name = "menuItem")
    private List<MenuItemXML> menuItems;
}
