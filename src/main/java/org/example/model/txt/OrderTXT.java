package org.example.model.txt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderTXT {

    private int id;
    private int table_id;
    private int customer_id;
    private LocalDate order_date;
    private Double total;

    public OrderTXT (String linea){
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.table_id = Integer.parseInt(valor[1]);
        this.customer_id = Integer.parseInt(valor[2]);
        this.order_date = LocalDate.parse(valor[3]);
        this.total = Double.parseDouble(valor[4]);
    }



    @Override
    public String toString() {
        return "Order: " + id + " - Date: " + order_date + " - Total: " + total;
    }

    public String toStringFile(){
        return id + ";" + table_id + ";" + customer_id + ";" + order_date + ";" + total;
    }
}
