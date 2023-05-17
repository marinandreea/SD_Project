package com.example.Assignment_A3.stateDesignPattern;

public class Package {

    private PackageState state = new OrderedState();

    public PackageState getState() {
        return state;
    }

    public void setState(PackageState state) {
        this.state = state;
    }

    public void previousState(){
        state.prev(this);
    }
    public void nextState(){
        state.next(this);
    }

    public String printStatus(){
        return state.printStatus();

    }
}
