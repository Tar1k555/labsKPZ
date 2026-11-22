package com.example.task3_strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumLeftTurnsStrategy implements RouteStrategy {
    @Override
    public String getStrategyName() {
        return "Мінімум поворотів ліворуч (безпека та швидкість у місті)";
    }

    @Override
    public List<DeliveryStop> buildRoute(List<DeliveryStop> stops) {
        List<DeliveryStop> result = new ArrayList<>(stops);
        result.sort(Comparator.comparingInt(DeliveryStop::getLeftTurns));
        return result;
    }
}