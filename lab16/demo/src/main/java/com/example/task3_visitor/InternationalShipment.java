package com.example.task3_visitor;

public class InternationalShipment extends BaseShipment {
    private final double weightKg;
    private final String destinationCountry;
    private final String hsCode;
    private final double customsValueEur;

    public InternationalShipment(String trackingNumber, double distanceKm, double weightKg,
                                 String destinationCountry, String hsCode, double customsValueEur) {
        super(trackingNumber, distanceKm);
        this.weightKg = weightKg;
        this.destinationCountry = destinationCountry;
        this.hsCode = hsCode;
        this.customsValueEur = customsValueEur;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public String getHsCode() {
        return hsCode;
    }

    public double getCustomsValueEur() {
        return customsValueEur;
    }

    @Override
    public void accept(ShipmentVisitor visitor) {
        visitor.visit(this);
    }
}