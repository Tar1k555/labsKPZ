package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class RoutePlanner {
    public void planRoute(String waybillNum, String destination) {
        ConsoleLogger.log("RoutePlanner", "Побудовано оптимальний маршрут для " + waybillNum + " до точки: " + destination);
    }
}