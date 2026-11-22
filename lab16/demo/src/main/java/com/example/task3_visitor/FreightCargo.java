package com.example.task3_visitor;

public class FreightCargo extends BaseShipment {
    private final double weightKg;
    private final int palletsCount;

    public FreightCargo(String trackingNumber, double distanceKm, double weightKg, int palletsCount) {
        super(trackingNumber, distanceKm);
        this.weightKg = weightKg;
        this.palletsCount = palletsCount;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public int getPalletsCount() {
        return palletsCount;
    }

    @Override
    public void accept(ShipmentVisitor visitor) {
        visitor.visit(this);
    }
}