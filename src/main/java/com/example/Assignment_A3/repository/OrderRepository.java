package com.example.Assignment_A3.repository;

import com.example.Assignment_A3.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
