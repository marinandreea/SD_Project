package com.example.Assignment_A3.controller;

import com.example.Assignment_A3.model.User;
import com.example.Assignment_A3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/cashier")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/cashier/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<User> getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/cashier/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/cashier/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/cashier/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @RequestMapping("/employeeActivityReport")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String generateEmployeeActivityReport() throws IOException {
        return userService.generateReportEmployeeActivity();
    }
}
