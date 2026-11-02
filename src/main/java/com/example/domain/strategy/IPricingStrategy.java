package com.example.domain.strategy;

public interface IPricingStrategy {
    double calculateBaseAmount(double baseMonthlyRate, int monthsDuration, boolean personalTrainerIncluded);
}