package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class CostCalculator {
    public double calculate(double weightKg, double distanceKm) {
        double cost = 45.0 + (weightKg * 12.0) + (distanceKm * 1.5);
        ConsoleLogger.log("CostCalculator", String.format("Розраховано вартість (вага %.1f кг, відстань %.1f км): %.2f грн", weightKg, distanceKm, cost));
        return cost;
    }
}