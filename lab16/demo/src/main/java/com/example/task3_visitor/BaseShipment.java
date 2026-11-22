package com.example.task3_visitor;

public abstract class BaseShipment implements ShipmentElement {
    private final String trackingNumber;
    private final double distanceKm;

    // Стан об'єкта, що змінюється після проходу відповідних відвідувачів
    private double calculatedCost = 0.0;
    private String customsStatus = "Не перевірялось";
    private double carbonFootprintKg = 0.0;

    protected BaseShipment(String trackingNumber, double distanceKm) {
        this.trackingNumber = trackingNumber;
        this.distanceKm = distanceKm;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public double getCalculatedCost() {
        return calculatedCost;
    }

    public void setCalculatedCost(double calculatedCost) {
        this.calculatedCost = calculatedCost;
    }

    public String getCustomsStatus() {
        return customsStatus;
    }

    public void setCustomsStatus(String customsStatus) {
        this.customsStatus = customsStatus;
    }

    public double getCarbonFootprintKg() {
        return carbonFootprintKg;
    }

    public void setCarbonFootprintKg(double carbonFootprintKg) {
        this.carbonFootprintKg = carbonFootprintKg;
    }

    @Override
    public String getStateSummary() {
        return String.format("%s [Вартість: %.2f грн | Митниця: %s | CO2: %.2f кг]",
                trackingNumber, calculatedCost, customsStatus, carbonFootprintKg);
    }
}