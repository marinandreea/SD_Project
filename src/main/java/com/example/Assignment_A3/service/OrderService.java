package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Cart;
import com.example.Assignment_A3.model.Client;
import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.model.dto.OrderDTO;
import com.example.Assignment_A3.repository.CartRepository;
import com.example.Assignment_A3.repository.ClientRepository;
import com.example.Assignment_A3.repository.OrderRepository;
import com.example.Assignment_A3.repository.UserRepository;
import com.example.Assignment_A3.service.observer.Subject;
import com.example.Assignment_A3.stateDesignPattern.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService extends Subject {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;

    public OrderService(OrderRepository orderRepository) throws IOException {
        super();
        this.orderRepository = orderRepository;
    }

    public Order toEntity(OrderDTO orderDTO){
        Optional<User> user = userRepository.findById(orderDTO.getIdCashier());
        Optional<Client> client = clientRepository.findById(orderDTO.getIdClient());
        Optional<Cart> cart = cartRepository.findById(orderDTO.getIdCart());

        return Order.builder()
                .user(user.get())
                .client(client.get())
                .cart(cart.get())
                .date(orderDTO.getDate())
                .totalPrice(orderDTO.getTotalPrice())
                .status(orderDTO.getStatus())
                .build();
    }

    public static OrderDTO fromEntity(Order order){
        return OrderDTO.builder()
                .idOrder(order.getIdOrder())
                .idCashier(order.getUser().getIdUser())
                .idClient(order.getClient().getIdClient())
                .idCart(order.getCart().getIdCart())
                .date(order.getDate())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }

    public boolean checkPresence(int idOrder){

        boolean exists = false;

        List<Order> orders = (List<Order>) orderRepository.findAll();
        for(Order o:orders){
            if(o.getIdOrder() == idOrder){
                exists = true;
            }
        }
        return exists;

    }

    public List<OrderDTO> getAllOrders(){
        List<Order> a = (List<Order>) orderRepository.findAll();
        return a.stream().map(OrderService::fromEntity).collect(Collectors.toList());
    }

    public List<Order> getAllOrdersss(){
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(o-> {

            orders.add(o);

        });
        return orders;
    }

    public String getOrderById(int id){
        var o = orderRepository.findById(id);
        if(o.isPresent()) {
            List<Order> orders = (List<Order>) orderRepository.findAll();
            for (Order oo : orders) {
                if (fromEntity(oo).getIdOrder() == id) {
                    return fromEntity(oo).toString();
                }
            }
        }
        return null;

    }

    public String createOrder(OrderDTO orderDTO){
        Package pkg = new Package();

       Order order = toEntity(orderDTO);

       Optional<User> user = userRepository.findById(order.getUser().getIdUser());
       user.get().setActivity(user.get().getActivity()+1);
       userRepository.save(user.get());
       order.setTotalPrice(order.getCart().getPrice());
       order.setStatus(pkg.printStatus());
       orderRepository.save(order);
       order.getClient().setLoyalty(order.getClient().getLoyalty()+1);
       clientRepository.save(order.getClient());
       return "Order was created successfully!";
    }

    public String deleteOrder(int id){
        if(checkPresence(id)){
        orderRepository.deleteById(id);
        return "Order was deleted successfully!";
        }else{
            return "Order does not exist!";
        }
    }

    public String getClientsWithPromotion() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Clients that can benefit of the 50% promotion are:\n");
        List<Client> clients = (List<Client>) clientRepository.findAll();
        for(Client c: clients){
            if(c.getLoyalty() % 10 == 0){
                stringBuilder.append("- id: " + c.getIdClient() + ", " + c.getFirstName() + " " + c.getLastName() + "\n");
            }
        }
        if(stringBuilder.isEmpty()) {
            stringBuilder.append("No client will benefit of the 50% promotion!");
        }
        notifyObservers(2,stringBuilder.toString());
        return stringBuilder.toString();

    }

    public String activatePromotion(int idOrder){


        if(checkPresence(idOrder)){
            Optional<Order> order = orderRepository.findById(idOrder);
            order.get().setTotalPrice(order.get().getTotalPrice()/2);
            orderRepository.save(order.get());
            return "Promotion of 50% was activated on order with id " + idOrder + "!";
        }else{
            return "Order with id " + idOrder + " does not exist!";
        }
    }

    public String finalizeOrder(int idOrder){
        Package pkg = new Package();
        pkg.nextState();
        if(checkPresence(idOrder)){
            Optional<Order> order = orderRepository.findById(idOrder);
            order.get().setStatus(pkg.printStatus());
            orderRepository.save(order.get());
            return "Order finalized!";
        }else{
            return "Order with id " + idOrder + " does not exist!";
        }
    }

}
