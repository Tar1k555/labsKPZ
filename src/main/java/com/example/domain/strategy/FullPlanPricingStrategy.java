package com.example.domain.strategy;

public class FullPlanPricingStrategy implements IPricingStrategy {
    @Override
    public double calculateBaseAmount(double baseMonthlyRate, int monthsDuration, boolean personalTrainerIncluded) {
        double rawCost = baseMonthlyRate * monthsDuration;
        if (personalTrainerIncluded) {
            rawCost += 3000.0;
        }
        return rawCost;
    }
}