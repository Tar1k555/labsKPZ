package com.example.domain.strategy;

public class VipPlanPricingStrategy implements IPricingStrategy {
    @Override
    public double calculateBaseAmount(double baseMonthlyRate, int monthsDuration, boolean personalTrainerIncluded) {
        double rawCost = (baseMonthlyRate * monthsDuration) + 5000.0;
        if (personalTrainerIncluded) {
            rawCost += 3000.0;
        }
        return rawCost;
    }
}