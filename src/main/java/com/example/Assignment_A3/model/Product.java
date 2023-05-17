package com.example.Assignment_A3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idProduct;
    @Column
    private String productName;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private int stockAvailable;
    @Column
    private int stockBought;
    @Column
    private int sellingRate;

}
