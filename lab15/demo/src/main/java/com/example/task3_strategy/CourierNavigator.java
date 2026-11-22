package com.example.task3_strategy;

import com.example.util.ConsoleLogger;
import java.util.List;

public class CourierNavigator {
    private final String courierName;
    private RouteStrategy strategy;

    public CourierNavigator(String courierName, RouteStrategy initialStrategy) {
        this.courierName = courierName;
        this.strategy = initialStrategy;
        ConsoleLogger.log("Navigator:" + courierName, "Ініціалізовано зі стратегією: [" + initialStrategy.getStrategyName() + "]");
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
        ConsoleLogger.log("Navigator:" + courierName, "ЗМІНА СТРАТЕГІЇ на: [" + strategy.getStrategyName() + "]");
    }

    public void buildAndDisplayRoute(List<DeliveryStop> stops) {
        ConsoleLogger.log("Navigator:" + courierName, "Виконується алгоритм: [" + strategy.getStrategyName() + "]");
        List<DeliveryStop> plannedRoute = strategy.buildRoute(stops);
        for (int i = 0; i < plannedRoute.size(); i++) {
            System.out.printf("   %d. %s%n", i + 1, plannedRoute.get(i));
        }
    }
}