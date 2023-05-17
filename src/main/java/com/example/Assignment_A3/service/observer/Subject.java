package com.example.Assignment_A3.service.observer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Subject {

    private ObserverWrite observerWrite = new ObserverWrite();
    private ObserverSend observerSend = new ObserverSend();

    FileWriter file = new FileWriter("LogEventFile.txt");

    protected Subject() throws IOException {
    }

    public void attach(ObserverWrite write, ObserverSend send){
        observerWrite = write;
        observerSend = send;
    }

    public void notifyObservers(int id, String s) throws IOException {
        observerWrite.writeToFile(id,s,file);
        observerSend.sendEmail(id,s);
    }
}
