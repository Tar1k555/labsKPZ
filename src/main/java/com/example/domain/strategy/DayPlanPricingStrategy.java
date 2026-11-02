package com.example.domain.strategy;

public class DayPlanPricingStrategy implements IPricingStrategy {
    @Override
    public double calculateBaseAmount(double baseMonthlyRate, int monthsDuration, boolean personalTrainerIncluded) {
        double rawCost = (baseMonthlyRate * monthsDuration) * 0.80;
        if (personalTrainerIncluded) {
            rawCost += 3000.0;
        }
        return rawCost;
    }
}