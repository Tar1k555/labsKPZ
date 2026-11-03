package com.example.task1_prototype;

import com.example.util.ConsoleLogger;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRoute implements Cloneable {
    private String routeName;
    private String assignedDriver;
    private List<RouteStop> stops;
    private WeightConstraint weightConstraint;

    public DeliveryRoute(String routeName, List<RouteStop> stops, WeightConstraint weightConstraint) {
        ConsoleLogger.log("DeliveryRoute", "Розпочато дорогу побудову еталонного маршруту '" + routeName + "'...");
        try {
            Thread.sleep(800); // Емуляція тривалої побудови
        } catch (InterruptedException ignored) {}
        
        this.routeName = routeName;
        this.stops = stops;
        this.weightConstraint = weightConstraint;
        this.assignedDriver = "Не призначено";
        ConsoleLogger.log("DeliveryRoute", "Еталонний маршрут '" + routeName + "' успішно побудовано.");
    }

    private DeliveryRoute() {} // Для клонування

    public void addStop(RouteStop stop) { this.stops.add(stop); }
    public WeightConstraint getWeightConstraint() { return weightConstraint; }
    public void setAssignedDriver(String assignedDriver) { this.assignedDriver = assignedDriver; }

    @Override
    public DeliveryRoute clone() {
        DeliveryRoute cloned = new DeliveryRoute();
        cloned.routeName = this.routeName;
        cloned.assignedDriver = this.assignedDriver;
        
        // Глибоке клонування списку зупинок
        cloned.stops = new ArrayList<>();
        for (RouteStop stop : this.stops) {
            cloned.stops.add(stop.clone());
        }
        
        // Глибоке клонування об'єкта обмежень
        cloned.weightConstraint = this.weightConstraint.clone();
        return cloned;
    }

    public void printDetails(String label) {
        System.out.println("\n  === " + label + " ===");
        System.out.println("  Назва: " + routeName);
        System.out.println("  Кур'єр: " + assignedDriver);
        System.out.println("  Обмеження вагою: " + weightConstraint);
        System.out.println("  Зупинки: " + stops);
    }
}