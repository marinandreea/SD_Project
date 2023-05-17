package com.example.Assignment_A3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idOrder;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cashierId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cartId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clientId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;
//    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    //@JoinColumn(name = "cartId")
//   // @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Product> listOfProducts = new ArrayList<>();

    @Column
    private String date;
    @Column
    private double totalPrice = 0.0;
    @Column
    private String status;


    public String toString(){
        return "{\n" + " id order: " + idOrder
                + ";\n id cashier: " + user.getIdUser()
                + ";\n id client: " + client.getIdClient()
                + ";\n id cart: " + cart.getIdCart()
                + ";\n date: " + date
                + ";\n totalPrice: " + totalPrice
                + ";\n status: " + status
                + "\n}";
    }


    public String invoice(){

        return "Order with id " + idOrder + " was placed on " + date
                + "\n\n"+ "Order details:\n"
                + " -> Client: " + client.getFirstName() + " " + client.getLastName() +"\n"
                + " -> Cashier: " + user.getIdUser() + "\n"
                + " -> Total price: " + totalPrice + "\n"
                + " -> Status: " + status + "\n\n"
                + "THANK YOU FOR YOUR ORDER! :)";

    }

}
