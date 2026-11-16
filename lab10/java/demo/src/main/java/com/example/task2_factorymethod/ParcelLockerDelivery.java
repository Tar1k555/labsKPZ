package com.example.task2_factorymethod;

public class ParcelLockerDelivery implements Delivery {
    @Override
    public double calculateCost(double weightKg) {
        return 45.0 + (weightKg * 8.0);
    }

    @Override
    public int calculateDeliveryDays() {
        return 2;
    }

    @Override
    public String getExecutorType() {
        return "Автоматизований поштомат";
    }

    @Override
    public String getTypeName() {
        return "Доставка у поштомат";
    }
}