package com.example.task2_state;

public interface ShipmentState {
    String getStateName();
    void accept(ShipmentContext shipment);
    void dispatch(ShipmentContext shipment);
    void arriveAtBranch(ShipmentContext shipment);
    void deliver(ShipmentContext shipment);
    void changeAddress(ShipmentContext shipment, String newAddress);
    void reroute(ShipmentContext shipment, String newAddress);
    void passStorageDays(ShipmentContext shipment, int days);
}