package com.example.Assignment_A3;

import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.repository.OrderRepository;
import com.example.Assignment_A3.service.observer.Subject;
import com.example.Assignment_A3.stateDesignPattern.Package;
import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CSVFileGenerator extends Subject {

    @Autowired
    private OrderRepository orderRepository;

    protected CSVFileGenerator() throws IOException {
    }

    public void issueAnInvoice(List<Order> orders, int idOrder, Writer writer){
        try{
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            int ok=0;
            Package pkg = new Package();
            pkg.nextState();
            pkg.nextState();
            for(Order o:orders){
                if(o.getIdOrder() == idOrder){
                    ok=1;
                    o.setStatus(pkg.printStatus());
                    orderRepository.save(o);
                    notifyObservers(1,o.invoice());
                    printer.printRecord(o.toString());
                }
            }
            if(ok==0){
                printer.printRecord("No order with id " + idOrder + " was found!");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
