package com.example;

import com.example.task2_state.ShipmentContext;
import com.example.task3_strategy.*;
import com.example.util.ConsoleLogger;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №15. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");

        runTask2Demo();
        System.out.println("\n-----------------------------------------------------\n");
        runTask3Demo();
    }

    private static void runTask2Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 2: ПАТЕРН STATE (СТАН ВІДПРАВЛЕННЯ) ===");

        // 1. Повний життєвий цикл до стану «Вручено»
        ConsoleLogger.log("Runner", "--- Сценарій 1: Проходження посилки через стани до 'Вручено' ---");
        ShipmentContext parcel1 = new ShipmentContext("WB-7001", "м. Чернівці, Відділення №1");
        parcel1.changeAddress("м. Чернівці, Відділення №4"); // Дозволено до відправки
        parcel1.accept();
        parcel1.dispatch();
        parcel1.arriveAtBranch();
        parcel1.deliver();

        // 2. Спроба змінити адресу в дорозі -> відмова, переадресація -> успішна
        System.out.println();
        ConsoleLogger.log("Runner", "--- Сценарій 2: Спроба зміни адреси в дорозі та переадресація ---");
        ShipmentContext parcel2 = new ShipmentContext("WB-7002", "м. Львів, Відділення №12");
        parcel2.accept();
        parcel2.dispatch();
        parcel2.changeAddress("м. Київ, Відділення №5"); // Має бути відмова
        parcel2.reroute("м. Київ, Відділення №5");       // Має бути успіх

        // 3. Зберігання 5 днів у відділенні -> автоматичне повернення відправнику
        System.out.println();
        ConsoleLogger.log("Runner", "--- Сценарій 3: Прострочення зберігання (5 днів) у відділенні ---");
        ShipmentContext parcel3 = new ShipmentContext("WB-7003", "м. Івано-Франківськ, Відділення №3");
        parcel3.accept();
        parcel3.dispatch();
        parcel3.arriveAtBranch();
        parcel3.passStorageDays(3);
        parcel3.passStorageDays(2); // Сумарно 5 днів -> перехід у 'Повернення відправнику'
        parcel3.deliver();          // Спроба вручити після повернення -> відмова
    }

    private static void runTask3Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 3: ПАТЕРН STRATEGY (МАРШРУТ КУР'ЄРА) ===");

        List<DeliveryStop> dailyAddresses = List.of(
                new DeliveryStop("вул. Головна, 120", false, 3.2, 4, 9, 1.45),
                new DeliveryStop("пр-т Незалежності, 88", true, 6.5, 2, 7, 2.10),
                new DeliveryStop("вул. Руська, 45", false, 1.8, 5, 8, 0.95),
                new DeliveryStop("вул. Героїв Майдану, 15", true, 4.1, 1, 4, 1.20),
                new DeliveryStop("вул. Кобилянської, 10", false, 2.4, 0, 2, 0.55),
                new DeliveryStop("вул. Садова, 7", false, 5.0, 3, 3, 0.80)
        );

        // 1. Побудова маршруту для 6 адрес за двома принципами
        ConsoleLogger.log("Runner", "--- Крок 1: Побудова маршруту за двома базовими принципами ---");
        CourierNavigator navigator = new CourierNavigator("Кур'єр Андрій", new ShortestDistanceStrategy());
        navigator.buildAndDisplayRoute(dailyAddresses);

        System.out.println();
        navigator.setStrategy(new UrgentPriorityStrategy());
        navigator.buildAndDisplayRoute(dailyAddresses);

        // 2. Зміна принципу у п'ятницю на «Урахування заторів»
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 2: П'ятниця вечір -> зміна принципу на 'Урахування заторів' ---");
        navigator.setStrategy(new TrafficAwareStrategy());
        navigator.buildAndDisplayRoute(dailyAddresses);

        // 3. Додавання принципу «Екологічний маршрут» одним класом
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 3: Підключення нового принципу 'Екологічний маршрут' ---");
        navigator.setStrategy(new EcoRouteStrategy());
        navigator.buildAndDisplayRoute(dailyAddresses);
    }
}