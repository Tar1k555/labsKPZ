package com.example.task3_strategy;

import java.util.List;

public interface RouteStrategy {
    String getStrategyName();
    List<DeliveryStop> buildRoute(List<DeliveryStop> stops);
}