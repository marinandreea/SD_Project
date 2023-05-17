package com.example.Assignment_A3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private int idOrder;
    private int idCashier;
    private int idClient;
    private int idCart;
    private String date;
    private double totalPrice;
    private String status;

    public String toString(){
        return "{\n" + "id order: " + idOrder
                + ";\n id cashier: " + idCashier
                + ";\n id client: " + idClient
                + ";\n id cart: " + idCart
                + ";\n date: " + date
                + ";\n totalPrice: " + totalPrice
                + ";\n status: " + status
                + "\n}";
    }


}
