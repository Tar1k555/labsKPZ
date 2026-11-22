package com.example.task3_visitor;

public class Letter extends BaseShipment {
    private final boolean registered;

    public Letter(String trackingNumber, double distanceKm, boolean registered) {
        super(trackingNumber, distanceKm);
        this.registered = registered;
    }

    public boolean isRegistered() {
        return registered;
    }

    @Override
    public void accept(ShipmentVisitor visitor) {
        visitor.visit(this);
    }
}