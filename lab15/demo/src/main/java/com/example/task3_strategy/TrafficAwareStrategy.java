package com.example.task3_strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrafficAwareStrategy implements RouteStrategy {
    @Override
    public String getStrategyName() {
        return "Урахування заторів у реальному часі";
    }

    @Override
    public List<DeliveryStop> buildRoute(List<DeliveryStop> stops) {
        List<DeliveryStop> result = new ArrayList<>(stops);
        result.sort(Comparator.comparingInt(DeliveryStop::getTrafficLevel));
        return result;
    }
}