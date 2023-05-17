package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Client;
import com.example.Assignment_A3.model.Role;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean checkPresence(int idUser){

        boolean exists = false;

        List<Client> clients = (List<Client>) clientRepository.findAll();
        for(Client c:clients){
            if(c.getIdClient() == idUser){
                exists = true;
            }
        }
        return exists;

    }

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(c-> {

                clients.add(c);

        });
        return clients;
    }

    public Optional<Client> getClientById(int id){
        var c = clientRepository.findById(id);
        if(c.isPresent()){
            return clientRepository.findById(id);
        }else{
            return null;
        }

    }

    public String addClient(Client client){
        if(!checkPresence(client.getIdClient())){
            clientRepository.save(client);
            return "A new client with id "+client.getIdClient()+" was created!";

        }else{
            return "A client with id " + client.getIdClient()+" was already created";
        }

    }

    public String updateClient(Client client){

        var c = clientRepository.findById(client.getIdClient());
        if(c.isPresent()){
            clientRepository.save(client);
            return "Client with id " + client.getIdClient() +" was updated successfully!";
        }else{
            return "Client not found!";
        }
    }

    public String deleteClient(int id){
        var c = clientRepository.findById(id);
        if(c.isPresent()){
            clientRepository.deleteById(id);
            return "Client with id " + id + " was successfully deleted!";
        }else{
            return "Client not found!";
        }
    }
}
