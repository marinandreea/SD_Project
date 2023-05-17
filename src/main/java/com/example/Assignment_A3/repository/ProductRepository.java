package com.example.Assignment_A3.repository;

import com.example.Assignment_A3.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
