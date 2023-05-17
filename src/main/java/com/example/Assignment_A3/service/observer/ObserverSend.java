package com.example.Assignment_A3.service.observer;

import com.example.Assignment_A3.model.Order;
import com.example.Assignment_A3.service.OrderService;
import com.example.Assignment_A3.stateDesignPattern.Package;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;


public class ObserverSend implements IObserverSend{


    @Override
    public void sendEmail(int i, String s) throws IOException {

        System.out.println(s);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("assignment3SoftDesign@gmail.com");
        mailSender.setPassword("mfnrpdwjvygaksin");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        mailSender.setJavaMailProperties(properties);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("assignment3SoftDesign@gmail.com");
        message.setTo("assignment3SoftDesign@gmail.com");

        if (i == 1) {
            message.setSubject("INVOICE ISSUED FOR ORDER");

//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append(s).append("\n");
//
//        Package pkg = new Package();
//
//        stringBuilder.append("\n1. ").append(pkg.printStatus()).append("\n");
//
//        pkg.nextState();
//        stringBuilder.append("2. ").append(pkg.printStatus()).append("\n");
//
//        pkg.nextState();
//        stringBuilder.append("3. ").append(pkg.printStatus()).append("\n");
//
//        pkg.nextState();
        // pkg.printStatus();

        message.setText(s);
        mailSender.send(message);
    }else if(i==2){
            message.setSubject("ACTIVATE PROMOTION");

           // String s2 = orderService.getClientsWithPromotion();
            message.setText(s);
            mailSender.send(message);
        }

    }
}
