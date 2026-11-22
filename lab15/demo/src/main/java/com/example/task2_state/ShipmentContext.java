package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class ShipmentContext {
    private final String trackingNumber;
    private String deliveryAddress;
    private int daysAtBranch;
    private ShipmentState currentState;

    public ShipmentContext(String trackingNumber, String deliveryAddress) {
        this.trackingNumber = trackingNumber;
        this.deliveryAddress = deliveryAddress;
        this.daysAtBranch = 0;
        this.currentState = new CreatedState();
        ConsoleLogger.log("Shipment:" + trackingNumber,
                "Створено відправлення на адресу «" + deliveryAddress + "». Початковий стан: [" + currentState.getStateName() + "]");
    }

    public void setState(ShipmentState newState) {
        String oldStateName = this.currentState.getStateName();
        this.currentState = newState;
        ConsoleLogger.log("Shipment:" + trackingNumber,
                "ПЕРЕХІД СТАНУ: [" + oldStateName + "] -> [" + newState.getStateName() + "]");
    }

    public void accept() {
        currentState.accept(this);
    }

    public void dispatch() {
        currentState.dispatch(this);
    }

    public void arriveAtBranch() {
        currentState.arriveAtBranch(this);
    }

    public void deliver() {
        currentState.deliver(this);
    }

    public void changeAddress(String newAddress) {
        currentState.changeAddress(this, newAddress);
    }

    public void reroute(String newAddress) {
        currentState.reroute(this, newAddress);
    }

    public void passStorageDays(int days) {
        currentState.passStorageDays(this, days);
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getDaysAtBranch() {
        return daysAtBranch;
    }

    public void addDaysAtBranch(int days) {
        this.daysAtBranch += days;
    }

    public String getCurrentStateName() {
        return currentState.getStateName();
    }
}