package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Cart;
import com.example.Assignment_A3.model.Product;
import com.example.Assignment_A3.repository.CartRepository;
import com.example.Assignment_A3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public boolean checkPresence(int idCart){

        boolean exists = false;

        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        for(Cart c:carts){
            if(c.getIdCart() == idCart){
                exists = true;
            }
        }
        return exists;

    }

    public List<Cart> getAllCarts(){
        List<Cart> carts = new ArrayList<>();
        cartRepository.findAll().forEach(c-> carts.add(c));
        return carts;
    }

    public Optional<Cart> getCartById(int id){
        var c = cartRepository.findById(id);
        if(c.isPresent()){
            return cartRepository.findById(id);
        }else{
            return null;
        }

    }

    public String createCart(Cart cart){

        if(!checkPresence(cart.getIdCart())){
            cartRepository.save(cart);
            return "A new cart with id " + cart.getIdCart() + " was created successfully!";
        }else{
            return "A cart with id " + cart.getIdCart() + " already exists!";
        }
    }
    public String addProductToCart(int idCart, int idProduct, int stockProduct){
        Optional<Cart> cart = cartRepository.findById(idCart);
        Optional<Product> product = productRepository.findById(idProduct);

        if(cart != null){
            if(product != null){
                if(product.get().getStockAvailable() - stockProduct >= 0){
              //  Optional<Product> product1 = Optional.of(new Product(product.get().getIdProduct(), product.get().getProductName(), product.get().getDescription(), product.get().getPrice(), product.get().getStockAvailable(),product.get().getStockBought()));
                product.get().setStockBought(stockProduct);
                product.get().setSellingRate(product.get().getSellingRate() + 1);
                cart.get().getListOfProducts().add(product.get());
                cart.get().setPrice(cart.get().getPrice() + stockProduct * product.get().getPrice());
                    cartRepository.save(cart.get());

                product.get().setStockAvailable(product.get().getStockAvailable() - stockProduct);

                productRepository.save(product.get());

                return "Product was added successfully!";
                }else{
                    return "Not enough products in stock!";
                }

            }else{
                return "Product does not exist!";
            }
        }else{
            return "Cart does not exist!";
        }

    }

    public String updateCart(int idCart, int idProduct, int stockProduct){

        Optional<Cart> cart = cartRepository.findById(idCart);
        Optional<Product> product = productRepository.findById(idProduct);
        System.out.println(product.get().getIdProduct());

        if(cart != null) {
            if (product.get() != null) {
                if (product.get().getStockAvailable() - stockProduct >= 0) {
                    for(Product pp:cart.get().getListOfProducts()){
                        System.out.println(pp.getIdProduct()+" ");
                    }
                    for (Product p : cart.get().getListOfProducts()) {
                        if (p.getIdProduct() == idProduct) {
                            if(stockProduct == -(p.getStockBought())){
                                p.setSellingRate(p.getSellingRate()-1);
                            }
                            int diff = p.getStockBought() - stockProduct;
                            p.setStockBought(stockProduct);
                            p.setStockAvailable(diff+p.getStockAvailable());
                            double diff2 = cart.get().getPrice() + (stockProduct * p.getPrice());
                            cart.get().setPrice(diff2);
                            productRepository.save(p);
                            cartRepository.save(cart.get());
                            return "Cart updated successfully!";
                        } else {
                            return "Product does not exist in cart!";
                        }
                    }
                } else {
                    return "Not enough products in stock!";
                }

            } else {
                return "Product does not exist!";
            }


        }
        return "Cart does not exist!";
    }

    public String deleteCart(int id){
        var c = cartRepository.findById(id);
        if(c.isPresent()){
            cartRepository.deleteById(id);
            return "Cart with id " + id + " was successfully deleted!";
        }else{
            return "Cart not found!";
        }
    }


}
