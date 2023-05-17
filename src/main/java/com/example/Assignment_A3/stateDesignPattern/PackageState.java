package com.example.Assignment_A3.stateDesignPattern;

public interface PackageState {

    void prev(Package pkg);
    void next(Package pkg);
    String printStatus();
}
