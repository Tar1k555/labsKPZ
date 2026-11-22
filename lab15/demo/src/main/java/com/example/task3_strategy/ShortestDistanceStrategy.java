package com.example.task3_strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShortestDistanceStrategy implements RouteStrategy {
    @Override
    public String getStrategyName() {
        return "Найкоротша відстань (за кілометражем)";
    }

    @Override
    public List<DeliveryStop> buildRoute(List<DeliveryStop> stops) {
        List<DeliveryStop> result = new ArrayList<>(stops);
        result.sort(Comparator.comparingDouble(DeliveryStop::getDistanceKm));
        return result;
    }
}