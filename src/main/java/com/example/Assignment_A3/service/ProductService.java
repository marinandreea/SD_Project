package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Product;
import com.example.Assignment_A3.model.Role;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    FileWriter file1 = new FileWriter("ReportBestSellingProducts.txt");
    FileWriter file2 = new FileWriter("ReportProductsOutOfStock.txt");


    public ProductService(ProductRepository productRepository) throws IOException {
        this.productRepository = productRepository;
    }

    public boolean checkPresence(int idProduct){

        boolean exists = false;

        List<Product> products = (List<Product>) productRepository.findAll();
        for(Product p:products){
            if(p.getIdProduct() == idProduct){
                exists = true;
            }
        }
        return exists;

    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(p-> products.add(p));
        return products;
    }

    public Optional<Product> getProductById(int id){
        var p = productRepository.findById(id);
        if(p.isPresent()){
            return productRepository.findById(id);
        }else{
            return null;
        }

    }

    public String addProduct(Product product){
        if(!checkPresence(product.getIdProduct())){
            if(product.getStockAvailable() >=1){
                product.setSellingRate(0);
                productRepository.save(product);
                return "A new product with id "+product.getIdProduct()+" was created!";
            }else{
                return "Cannot add the product because the stock is below 1!";
            }

        }else{
            return "A product with id " + product.getIdProduct()+" was already created";
        }

    }

    public String updateProduct(Product product){

        var p = productRepository.findById(product.getIdProduct());
        if(p.isPresent()){
            productRepository.save(product);
            return "Product with id " + product.getIdProduct() +" was updated successfully!";
        }else{
            return "Product not found!";
        }
    }

    public String deleteProduct(int id){
        var p = productRepository.findById(id);
        if(p.isPresent()){
            productRepository.deleteById(id);
            return "Product with id " + id + " was successfully deleted!";
        }else{
            return "Product not found!";
        }
    }

    public String bestSellingProductsReport() throws IOException {
        List<Product> productList = (List<Product>) productRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;

        for(Product p: productList){
            if(p.getSellingRate() >= 5){
                stringBuilder.append(i).append(". ").append("id product: ").append(p.getIdProduct()).append(", ").append("name: ").append(p.getProductName())
                        .append(", ").append("selling rate: ").append(p.getSellingRate()).append("\n");
                i++;
            }
        }
        if(stringBuilder.isEmpty()){
            file1.write("There is no product having selling rate greater then 5!");
        }else{
            file1.write("List with products having selling rate >= 5: \n");
            file1.write(stringBuilder.toString());
            file1.close();
        }
        return "Report on best selling products was generated successfully!";
    }

    public String outOfStockProducts() throws IOException {
        List<Product> productList = (List<Product>) productRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;

        for(Product p: productList){
            if(p.getStockAvailable() == 0){
                stringBuilder.append(i).append(". ").append("id product: ").append(p.getIdProduct()).append(", ").append("name: ").append(p.getProductName())
                        .append(", ").append("selling rate: ").append(p.getSellingRate()).append("\n");
                i++;
            }
        }
        if(stringBuilder.isEmpty()){
            file2.write("There is no product out of stock");
        }else{
            file2.write("List with products out of stock: \n");
            file2.write(stringBuilder.toString());
            file2.close();
        }
        return "Report on products out of stock was generated successfully!";
    }





}
