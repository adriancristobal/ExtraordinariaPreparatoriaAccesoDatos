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
public class CustomerXML {
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
}
