package com.example.Assignment_A3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCart;
    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Product> listOfProducts = new ArrayList<>();
    @Column
    private double price;

    public String productsList(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Product p: listOfProducts){
            stringBuilder.append(p.getProductName()).append(" ").append(p.getDescription()).append(" ").append(p.getStockBought()).append(" ").append(p.getPrice()).append("\n");

        }
        return stringBuilder.toString();
    }
}
