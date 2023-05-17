package com.example.Assignment_A3.controller;

import com.example.Assignment_A3.model.Product;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping("/product/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<Product> getProduct(@PathVariable int id){
        return productService.getProductById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/product/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/product/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/product/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @RequestMapping("/bestSellingReport")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String generateBSReport() throws IOException {
        return productService.bestSellingProductsReport();
    }

    @RequestMapping("/outOfStockReport")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String generateOutOfStockReport() throws IOException {
        return productService.outOfStockProducts();
    }


}
