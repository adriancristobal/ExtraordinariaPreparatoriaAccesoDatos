package org.example.model.txt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MenuItemTXT {

    private int id;
    private String name;
    private String description;
    private Double price;

    public MenuItemTXT(String linea) {
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.name = valor[1];
        this.description = valor[2];
        this.price = Double.parseDouble(valor[3]);
    }

    @Override
    public String toString() {
        return "MenuItem: " + id + " - Name: " + name + " - Description: " + description + " - Price: " + price;
    }

    public String toStringFile() {
        return id + ";" + name + ";" + description + ";" + price;
    }

}
