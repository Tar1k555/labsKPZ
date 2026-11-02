package com.example;

import com.example.task2_factorymethod.*;
import com.example.util.ConsoleLogger;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №10. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");
        
        runTask2Demo();
    }

    private static void runTask2Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 2: ПАТЕРН FACTORY METHOD ===");

        String shipmentId = "WB-2001";
        String address = "м. Чернівці, вул. Університетська, 28";
        double weightKg = 5.0;

        // 1. Оформлення відправлення 3 стандартними способами
        DeliveryProcess courier = new CourierDeliveryProcess();
        courier.registerDelivery(shipmentId, address, weightKg);

        DeliveryProcess locker = new ParcelLockerDeliveryProcess();
        locker.registerDelivery(shipmentId, address, weightKg);

        DeliveryProcess branch = new BranchDeliveryProcess();
        branch.registerDelivery(shipmentId, address, weightKg);

        // 2 & 3. Додавання та оформлення новим способом "Доставка на поверх" без зміни загального коду
        ConsoleLogger.log("Runner", "--- РОЗШИРЕННЯ: Оформлення новим способом 'Доставка на поверх' ---");
        DeliveryProcess floor = new FloorDeliveryProcess();
        floor.registerDelivery(shipmentId, address, weightKg);
    }
}