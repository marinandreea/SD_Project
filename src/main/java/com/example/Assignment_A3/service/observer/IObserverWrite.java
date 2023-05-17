package com.example.Assignment_A3.service.observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface IObserverWrite {

    void writeToFile(int id, String s, FileWriter file) throws IOException;
}
