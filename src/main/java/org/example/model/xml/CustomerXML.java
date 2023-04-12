package org.example.model.xml;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "first_name", "last_name", "email", "phone", "order"})
public class CustomerXML {
    @XmlElement(name = "firstName")
    private String first_name;
    @XmlElement(name = "lastName")
    private String last_name;
    private String email;
    private String phone;
    private List<OrderXML> order;
}
