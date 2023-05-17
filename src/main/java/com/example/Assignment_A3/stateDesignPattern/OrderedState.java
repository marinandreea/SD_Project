package com.example.Assignment_A3.stateDesignPattern;

public class OrderedState implements PackageState{
    @Override
    public void prev(Package pkg) {

        System.out.println("The order is in its root state");
    }

    @Override
    public void next(Package pkg) {
        pkg.setState(new FinalizedState());
    }

    @Override
    public String printStatus() {
        return "Order created, not finalized yet!";
    }
}
