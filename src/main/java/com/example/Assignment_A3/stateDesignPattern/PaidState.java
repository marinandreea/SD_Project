package com.example.Assignment_A3.stateDesignPattern;

public class PaidState implements PackageState {
    @Override
    public void prev(Package pkg) {
        pkg.setState(new FinalizedState());
    }

    @Override
    public void next(Package pkg) {
        System.out.println("This order is already paid by the client");
    }

    @Override
    public String printStatus() {
        return "Order finalized and paid!" ;
    }
}
