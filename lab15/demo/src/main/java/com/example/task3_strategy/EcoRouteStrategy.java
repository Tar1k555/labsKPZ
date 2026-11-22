package com.example.task3_strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EcoRouteStrategy implements RouteStrategy {
    @Override
    public String getStrategyName() {
        return "Екологічний маршрут (мінімум викидів CO2)";
    }

    @Override
    public List<DeliveryStop> buildRoute(List<DeliveryStop> stops) {
        List<DeliveryStop> result = new ArrayList<>(stops);
        result.sort(Comparator.comparingDouble(DeliveryStop::getCo2EmissionKg));
        return result;
    }
}