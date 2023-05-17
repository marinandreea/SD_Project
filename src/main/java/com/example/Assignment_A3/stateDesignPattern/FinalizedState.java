package com.example.Assignment_A3.stateDesignPattern;

public class FinalizedState implements PackageState{
    @Override
    public void prev(Package pkg) {

        pkg.setState(new OrderedState());
    }

    @Override
    public void next(Package pkg) {
        pkg.setState(new PaidState());
    }

    @Override
    public String printStatus() {
        return "Order created and finalized, not paid yet!";
    }
}
