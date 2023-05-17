package com.example.Assignment_A3.repository;

import com.example.Assignment_A3.model.Client;
import com.example.Assignment_A3.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
