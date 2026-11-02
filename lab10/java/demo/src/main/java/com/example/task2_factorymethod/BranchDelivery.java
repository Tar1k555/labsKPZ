package com.example.task2_factorymethod;

public class BranchDelivery implements Delivery {
    @Override
    public double calculateCost(double weightKg) {
        return 35.0 + (weightKg * 5.0);
    }

    @Override
    public int calculateDeliveryDays() {
        return 2;
    }

    @Override
    public String getExecutorType() {
        return "Оператор відділення";
    }

    @Override
    public String getTypeName() {
        return "Доставка у відділення";
    }
}