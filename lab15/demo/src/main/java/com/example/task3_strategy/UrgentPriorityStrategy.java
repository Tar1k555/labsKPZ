package com.example.task3_strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UrgentPriorityStrategy implements RouteStrategy {
    @Override
    public String getStrategyName() {
        return "Пріоритет термінових відправлень";
    }

    @Override
    public List<DeliveryStop> buildRoute(List<DeliveryStop> stops) {
        List<DeliveryStop> result = new ArrayList<>(stops);
        result.sort(Comparator.comparing(DeliveryStop::isUrgent).reversed()
                .thenComparingDouble(DeliveryStop::getDistanceKm));
        return result;
    }
}