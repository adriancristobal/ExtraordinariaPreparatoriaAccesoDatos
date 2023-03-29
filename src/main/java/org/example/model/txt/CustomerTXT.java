package org.example.model.txt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerTXT {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    public CustomerTXT (String linea){
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.first_name = valor[1];
        this.last_name = valor[2];
        this.email = valor[3];
        this.phone = valor[4];
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }

    public String toStringFile(){
        return id + ";" + first_name + ";" + last_name + ";" + email + ";" + phone;
    }
}
