package com.example.task3_strategy;

public class DeliveryStop {
    private final String address;
    private final boolean urgent;
    private final double distanceKm;
    private final int leftTurns;
    private final int trafficLevel; // 1..10 балів заторів
    private final double co2EmissionKg;

    public DeliveryStop(String address, boolean urgent, double distanceKm, int leftTurns, int trafficLevel, double co2EmissionKg) {
        this.address = address;
        this.urgent = urgent;
        this.distanceKm = distanceKm;
        this.leftTurns = leftTurns;
        this.trafficLevel = trafficLevel;
        this.co2EmissionKg = co2EmissionKg;
    }

    public String getAddress() {
        return address;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public int getLeftTurns() {
        return leftTurns;
    }

    public int getTrafficLevel() {
        return trafficLevel;
    }

    public double getCo2EmissionKg() {
        return co2EmissionKg;
    }

    @Override
    public String toString() {
        return String.format("%s [термінове=%s, %.1f км, лівих поворотів=%d, затори=%d/10, CO2=%.2f кг]",
                address, urgent ? "ТАК" : "ні", distanceKm, leftTurns, trafficLevel, co2EmissionKg);
    }
}