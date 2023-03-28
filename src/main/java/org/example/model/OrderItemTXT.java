package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderItemTXT {

    private int id;
    private int order_id;
    private int menu_item_id;
    private int quantity;
    private Double price;


    public OrderItemTXT (String linea){
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.order_id = Integer.parseInt(valor[1]);
        this.menu_item_id = Integer.parseInt(valor[2]);
        this.quantity = Integer.parseInt(valor[3]);
        this.price = Double.parseDouble(valor[4]);
    }



    @Override
    public String toString() {
        return "OrderItem: " + id + " - Quantity: " + quantity + " - Price: " + price;
    }

    public String toStringFile(){
        return id + ";" + order_id + ";" + menu_item_id + ";" + quantity + ";" + price;
    }
}
