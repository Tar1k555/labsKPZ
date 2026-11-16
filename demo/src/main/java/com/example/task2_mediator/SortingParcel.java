package com.example.task2_mediator;

public class SortingParcel {
    private final String trackingNumber;
    private final double weightKg;
    private final String destination;

    public SortingParcel(String trackingNumber, double weightKg, String destination) {
        this.trackingNumber = trackingNumber;
        this.weightKg = weightKg;
        this.destination = destination;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return String.format("%s (вага: %.1f кг, напрямок: «%s»)", trackingNumber, weightKg, destination);
    }
}