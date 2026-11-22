package com.example.task3_visitor;

public class Parcel extends BaseShipment {
    private final double weightKg;
    private final double declaredValue;

    public Parcel(String trackingNumber, double distanceKm, double weightKg, double declaredValue) {
        super(trackingNumber, distanceKm);
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public double getDeclaredValue() {
        return declaredValue;
    }

    @Override
    public void accept(ShipmentVisitor visitor) {
        visitor.visit(this);
    }
}