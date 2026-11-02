package com.example.domain.strategy;

import java.util.Map;

public class PlanPricingFactory {
    private static final Map<String, IPricingStrategy> STRATEGIES = Map.of(
        "DAY", new DayPlanPricingStrategy(),
        "FULL", new FullPlanPricingStrategy(),
        "VIP", new VipPlanPricingStrategy()
    );

    public static IPricingStrategy getStrategy(String planCode) {
        if (planCode == null || !STRATEGIES.containsKey(planCode.toUpperCase())) {
            throw new IllegalArgumentException("Невідомий код тарифного плану: " + planCode);
        }
        return STRATEGIES.get(planCode.toUpperCase());
    }
}