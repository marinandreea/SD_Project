package com.example.Assignment_A3.service.observer;

import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.repository.OrderRepository;
import com.example.Assignment_A3.service.OrderService;
import com.example.Assignment_A3.stateDesignPattern.Package;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class ObserverWrite implements IObserverWrite{

    // id = 1 => cashier issues an invoice
    // id = 2 =? admin activates a promotion


    public OrderService orderService;

    @Override
    public void writeToFile(int id, String s, FileWriter file) throws IOException {


        if(id == 1){

//            StringBuilder stringBuilder = new StringBuilder();
//
//
//            Package pkg = new Package();
//
//            stringBuilder.append("\n1. ").append(pkg.printStatus()).append("\n");
//
//            pkg.nextState();
//            stringBuilder.append("2. ").append(pkg.printStatus()).append("\n");
//
//            pkg.nextState();
//            stringBuilder.append("3. ").append(pkg.printStatus()).append("\n");
//
//            pkg.nextState();
//            pkg.printStatus();

            file.write(s);
          //  file.write(stringBuilder.toString());
            file.close();
        }

        if(id == 2){
            //String s2 = orderService.getClientsWithPromotion();
            file.write(s);
            file.close();
        }

    }


}
