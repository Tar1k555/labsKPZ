package com.example;

import com.example.task1_prototype.*;
import com.example.task3_singleton.*;
import com.example.util.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №9. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");
        
        runTask1Demo();
        System.out.println("\n-----------------------------------------------------\n");
        runTask3Demo();
    }

    private static void runTask1Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 1: ПАТЕРН PROTOTYPE ===");
        
        // 1. Створення еталона
        List<RouteStop> masterStops = new ArrayList<>();
        masterStops.add(new RouteStop("Чернівці", "+380501112233"));
        masterStops.add(new RouteStop("Хотин", "+380502223344"));
        masterStops.add(new RouteStop("Кельменці", "+380503334455"));

        DeliveryRoute masterRoute = new DeliveryRoute("Чернівці – Хотин – Кельменці", masterStops, new WeightConstraint(1500));

        // 2. Клонування на 3 дні без повторної побудови
        DeliveryRoute day1Route = masterRoute.clone();
        DeliveryRoute day2Route = masterRoute.clone();
        DeliveryRoute day3Route = masterRoute.clone();

        // 3. Зміна денного маршруту (День 1)
        day1Route.setAssignedDriver("Петренко В.");
        day1Route.addStop(new RouteStop("Новоселиця", "+380504445566"));
        day1Route.getWeightConstraint().setMaxWeightKg(2000);

        // 4. Демонстрація незалежності еталона від клону
        masterRoute.printDetails("ЕТАЛОННИЙ МАРШРУТ (Без змін)");
        day1Route.printDetails("ЗМІНЕНИЙ МАРШРУТ ДЕНЬ 1");
    }

    private static void runTask3Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 3: ПАТЕРН SINGLETON ===");

        // Отримання посилання на службу з 3 різних місць
        WaybillNumberingService service1 = WaybillNumberingService.getInstance();
        WaybillNumberingService service2 = WaybillNumberingService.getInstance();
        WaybillNumberingService service3 = WaybillNumberingService.getInstance();

        // Демонстрація однакового ідентифікатора екземпляра
        ConsoleLogger.log("Runner", "ID служби Відділення №1: " + System.identityHashCode(service1));
        ConsoleLogger.log("Runner", "ID служби Відділення №2: " + System.identityHashCode(service2));
        ConsoleLogger.log("Runner", "ID служби Онлайн-Кабінету: " + System.identityHashCode(service3));

        // Почергова реєстрація по 3 накладних
        for (int i = 1; i <= 3; i++) service1.generateNextNumber("Відділення №1");
        for (int i = 1; i <= 3; i++) service2.generateNextNumber("Відділення №2");
        for (int i = 1; i <= 3; i++) service3.generateNextNumber("Онлайн-Кабінет");
    }
}