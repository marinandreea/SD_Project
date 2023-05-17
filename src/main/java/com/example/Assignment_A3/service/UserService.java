package com.example.Assignment_A3.service;

import com.example.Assignment_A3.model.Role;
import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    FileWriter file3 = new FileWriter("ReportEmployeeActivity.txt");

    public UserService(UserRepository userRepository) throws IOException {
        this.userRepository=userRepository;
    }

    public boolean checkPresence(int idUser){

        boolean exists = false;

        List<User> users = (List<User>) userRepository.findAll();
        for(User u:users){
            if(u.getIdUser() == idUser){
                exists = true;
            }
        }
        return exists;

    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(u-> {
            if(u.getRole().equals(Role.CASHIER)){
                users.add(u);
            }
        });
        return users;
    }

    public Optional<User> getUserById(int id){
        var u = userRepository.findById(id);
        if(u.isPresent()){
            return userRepository.findById(id);
        }else{
            return null;
        }

    }

    public String addUser(User user){
        if(!checkPresence(user.getIdUser())){
            String encP = user.encryptedPassword(user.getPassword());
            user.setPassword(encP);
            user.setRoles(Role.CASHIER);
            user.setActivity(0);
            userRepository.save(user);
            return "A new user with id "+user.getIdUser()+" was created!";

        }else{
            return "A user with id " + user.getIdUser()+" was already created";
        }

    }

    public String updateUser(User user){

        var u = userRepository.findById(user.getIdUser());
        if(u.isPresent()){
            userRepository.save(user);
            return "User with id " + user.getIdUser() +" was updated successfully!";
        }else{
            return "User not found!";
        }
    }

    public String deleteUser(int id){
        var u = userRepository.findById(id);
        if(u.isPresent()){
            userRepository.deleteById(id);
            return "User with id " + id + " was successfully deleted!";
        }else{
            return "User not found!";
        }
    }

    public String generateReportEmployeeActivity() throws IOException {

        List<User> users = getAllUsers();

        StringBuilder stringBuilder = new StringBuilder();
        int i =1;

        for(User u: users){
            stringBuilder.append(i).append(". ").append("id: ").append(u.getIdUser()).append(", username: ").append(u.getUsername()).append(", selling activity: ").append(u.getActivity()).append("\n");
            i++;
        }
        if(stringBuilder.isEmpty()){
            file3.write("There is no cashier");
        }else{
            file3.write("List with employee activity: \n");
            file3.write(stringBuilder.toString());
            file3.close();
        }
        return "Report on employee activity was generated successfully!";
    }
}
