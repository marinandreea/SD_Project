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
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idClient;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int loyalty;
}
