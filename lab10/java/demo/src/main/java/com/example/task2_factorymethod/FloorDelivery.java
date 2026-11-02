package com.example.task2_factorymethod;

public class FloorDelivery implements Delivery {
    @Override
    public double calculateCost(double weightKg) {
        return 120.0 + (weightKg * 20.0);
    }

    @Override
    public int calculateDeliveryDays() {
        return 1;
    }

    @Override
    public String getExecutorType() {
        return "Кур'єр-вантажник (підйом на поверх)";
    }

    @Override
    public String getTypeName() {
        return "Доставка на поверх";
    }
}