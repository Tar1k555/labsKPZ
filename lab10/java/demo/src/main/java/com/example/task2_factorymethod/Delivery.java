package com.example.task2_factorymethod;

public interface Delivery {
    double calculateCost(double weightKg);
    int calculateDeliveryDays();
    String getExecutorType();
    String getTypeName();
}