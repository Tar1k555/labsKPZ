package com.example.task3_visitor;

import com.example.util.ConsoleLogger;

public class DeliveryCostVisitor implements ShipmentVisitor {
    private double totalBatchCost = 0.0;

    @Override
    public String getOperationName() {
        return "Розрахунок вартості доставки";
    }

    @Override
    public void visit(Letter letter) {
        double cost = letter.isRegistered() ? 45.0 : 25.0;
        letter.setCalculatedCost(cost);
        totalBatchCost += cost;
        ConsoleLogger.log("DeliveryCostVisitor",
                String.format("Лист %s (%s): нараховано %.2f грн",
                        letter.getTrackingNumber(), letter.isRegistered() ? "рекомендований" : "звичайний", cost));
    }

    @Override
    public void visit(Parcel parcel) {
        double cost = 70.0 + parcel.getWeightKg() * 12.0 + parcel.getDeclaredValue() * 0.01;
        parcel.setCalculatedCost(cost);
        totalBatchCost += cost;
        ConsoleLogger.log("DeliveryCostVisitor",
                String.format("Посилка %s (%.1f кг): нараховано %.2f грн",
                        parcel.getTrackingNumber(), parcel.getWeightKg(), cost));
    }

    @Override
    public void visit(FreightCargo cargo) {
        double cost = cargo.getPalletsCount() * 450.0 + cargo.getWeightKg() * 4.5 + cargo.getDistanceKm() * 2.0;
        cargo.setCalculatedCost(cost);
        totalBatchCost += cost;
        ConsoleLogger.log("DeliveryCostVisitor",
                String.format("Вантаж %s (%d пал., %.1f кг): нараховано %.2f грн",
                        cargo.getTrackingNumber(), cargo.getPalletsCount(), cargo.getWeightKg(), cost));
    }

    @Override
    public void visit(InternationalShipment international) {
        double cost = 650.0 + international.getWeightKg() * 85.0 + international.getDistanceKm() * 1.5;
        international.setCalculatedCost(cost);
        totalBatchCost += cost;
        ConsoleLogger.log("DeliveryCostVisitor",
                String.format("Міжнародне %s (-> %s, %.1f кг): нараховано %.2f грн",
                        international.getTrackingNumber(), international.getDestinationCountry(),
                        international.getWeightKg(), cost));
    }

    public double getTotalBatchCost() {
        return totalBatchCost;
    }
}