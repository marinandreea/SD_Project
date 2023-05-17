package com.example.Assignment_A3;

import com.example.Assignment_A3.auth.AuthenticationRequest;
import com.example.Assignment_A3.auth.AuthenticationService;
import com.example.Assignment_A3.controller.UserController;
import com.example.Assignment_A3.model.*;
import com.example.Assignment_A3.model.dto.OrderDTO;
import com.example.Assignment_A3.repository.*;
import com.example.Assignment_A3.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestFlow {

  //  @MockBean

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    UserController userController = new UserController();

    @Test
    public void addNewCashierTest(){
        User user = new User(4,"cashier.3","cashier3", Role.CASHIER,2);
        when(userRepository.save(user)).thenReturn(user);
       String ok = userService.addUser(user);

        assertEquals(ok,"A new user with id "+ user.getIdUser() + " was created!");

    }

    @Test
    public void createNewCartTest() throws IOException {
        User user = new User(4,"cashier.3","cashier3", Role.CASHIER,2);
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        userService.addUser(user);

        Client client = new Client(3,"Ana","Maria",0);
        ClientRepository clientRepository = mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository);
        clientService.addClient(client);


        Product product1 = new Product(3,"notebook","quality paper",3.5,50,0,5);
        Product product2 = new Product(4,"bag","pink",50,10,0,5);
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        productService.addProduct(product1);
        productService.addProduct(product2);


        List<Product> productList = new ArrayList<>();
        productList.add(product1); productList.add(product2);
        Cart cart = new Cart(3,productList,0);
        CartRepository cartRepository = mock(CartRepository.class);
        CartService cartService = new CartService(cartRepository);
        String s = cartService.createCart(cart);


        assertEquals(s,"A new cart with id " + cart.getIdCart() + " was created successfully!");

    }
}
