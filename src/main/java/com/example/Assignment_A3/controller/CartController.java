package com.example.Assignment_A3.controller;

import com.example.Assignment_A3.model.Cart;
import com.example.Assignment_A3.model.Product;
import com.example.Assignment_A3.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart")
    @PreAuthorize("hasAuthority('CASHIER')")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @RequestMapping("/cart/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public Optional<Cart> getCart(@PathVariable int id){
        return cartService.getCartById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/cart/create")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String addCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/cart/addProduct/{idCart}/{idProduct}/{stock}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String addProductToCart(@PathVariable int idCart,@PathVariable int idProduct, @PathVariable int stock ){
        return cartService.addProductToCart(idCart,idProduct,stock);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/cart/update/{idCart}/{idProduct}/{stock}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String updateCart(@PathVariable int idCart,@PathVariable int idProduct, @PathVariable int stock){
        return cartService.updateCart(idCart,idProduct,stock);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/cart/delete/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String deleteCart(@PathVariable int id){
        return cartService.deleteCart(id);
    }
}
