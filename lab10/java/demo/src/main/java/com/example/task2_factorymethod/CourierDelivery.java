package com.example.task2_factorymethod;

public class CourierDelivery implements Delivery {
    @Override
    public double calculateCost(double weightKg) {
        return 80.0 + (weightKg * 15.0);
    }

    @Override
    public int calculateDeliveryDays() {
        return 1;
    }

    @Override
    public String getExecutorType() {
        return "Кур'єр автопарку №1";
    }

    @Override
    public String getTypeName() {
        return "Кур'єрська доставка";
    }
}