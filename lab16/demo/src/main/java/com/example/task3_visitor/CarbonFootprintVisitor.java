package com.example.task3_visitor;

import com.example.util.ConsoleLogger;

public class CarbonFootprintVisitor implements ShipmentVisitor {
    private double totalCo2Kg = 0.0;

    @Override
    public String getOperationName() {
        return "Розрахунок вуглецевого сліду (CO2)";
    }

    @Override
    public void visit(Letter letter) {
        double co2 = letter.getDistanceKm() * 0.002;
        letter.setCarbonFootprintKg(co2);
        totalCo2Kg += co2;
        ConsoleLogger.log("CarbonVisitor",
                String.format("Лист %s (%.0f км): викиди = %.3f кг CO2", letter.getTrackingNumber(), letter.getDistanceKm(), co2));
    }

    @Override
    public void visit(Parcel parcel) {
        double co2 = parcel.getDistanceKm() * parcel.getWeightKg() * 0.015;
        parcel.setCarbonFootprintKg(co2);
        totalCo2Kg += co2;
        ConsoleLogger.log("CarbonVisitor",
                String.format("Посилка %s (%.1f кг, %.0f км): викиди = %.2f кг CO2",
                        parcel.getTrackingNumber(), parcel.getWeightKg(), parcel.getDistanceKm(), co2));
    }

    @Override
    public void visit(FreightCargo cargo) {
        double co2 = cargo.getDistanceKm() * cargo.getWeightKg() * 0.025;
        cargo.setCarbonFootprintKg(co2);
        totalCo2Kg += co2;
        ConsoleLogger.log("CarbonVisitor",
                String.format("Вантаж %s (%.1f кг, %.0f км): викиди = %.2f кг CO2",
                        cargo.getTrackingNumber(), cargo.getWeightKg(), cargo.getDistanceKm(), co2));
    }

    @Override
    public void visit(InternationalShipment international) {
        // Авіа/магістральне перевезення має вищий коефіцієнт викидів
        double co2 = international.getDistanceKm() * international.getWeightKg() * 0.060;
        international.setCarbonFootprintKg(co2);
        totalCo2Kg += co2;
        ConsoleLogger.log("CarbonVisitor",
                String.format("Міжнародне %s (%.1f кг, %.0f км): викиди = %.2f кг CO2",
                        international.getTrackingNumber(), international.getWeightKg(), international.getDistanceKm(), co2));
    }

    public double getTotalCo2Kg() {
        return totalCo2Kg;
    }
}