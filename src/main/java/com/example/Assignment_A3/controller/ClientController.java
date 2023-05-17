package com.example.Assignment_A3.controller;

import com.example.Assignment_A3.model.Client;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("client")
    @PreAuthorize("hasAuthority('CASHIER')")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @RequestMapping("/client/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public Optional<Client> getClient(@PathVariable int id){
        return clientService.getClientById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/client/create")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/client/update")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/client/delete/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String deleteClient(@PathVariable int id){
        return clientService.deleteClient(id);
    }
}
