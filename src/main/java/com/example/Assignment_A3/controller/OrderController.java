package com.example.Assignment_A3.controller;

import com.example.Assignment_A3.CSVFileGenerator;
import com.example.Assignment_A3.model.Cart;
import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.model.dto.OrderDTO;
import com.example.Assignment_A3.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CSVFileGenerator csvFileGenerator;

    @RequestMapping("/order")
    @PreAuthorize("hasAuthority('CASHIER')")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping("/order/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String getOrder(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/order/create")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String addOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/order/delete/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String deleteOrder(@PathVariable int id){
        return orderService.deleteOrder(id);
    }

    @GetMapping("export-to-csv/{idOrder}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public void issueInvoice(HttpServletResponse respons, @PathVariable int idOrder) throws IOException {
        respons.setContentType("invoice/csv");
        respons.addHeader("Content-Disposition", "attachment; filename=\"invoice.csv\"");
        csvFileGenerator.issueAnInvoice(orderService.getAllOrdersss(),idOrder,respons.getWriter());
    }

    @RequestMapping("/getClientsWithPromotion")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getClientsWithPromotion() throws IOException {return orderService.getClientsWithPromotion();}

    @RequestMapping(method = RequestMethod.PUT,value = "/activatePromotion/{idOrder}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String activatePromotion(@PathVariable int idOrder){
        return orderService.activatePromotion(idOrder);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/finalizeOrder/{idOrder}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String finalizeOrder(@PathVariable int idOrder){
        return orderService.finalizeOrder(idOrder);
    }


}
