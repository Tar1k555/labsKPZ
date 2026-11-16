package com.example;

import com.example.task1_flyweight.LogisticsMap;
import com.example.task1_flyweight.ShipmentMarker;
import com.example.task1_flyweight.ShipmentTypeFactory;
import com.example.task3_composite.CargoComponent;
import com.example.task3_composite.CargoContainer;
import com.example.task3_composite.Parcel;
import com.example.util.ConsoleLogger;

import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №12. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");

        runTask1Demo();
        System.out.println("\n-----------------------------------------------------\n");
        runTask3Demo();
    }

    private static void runTask1Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 1: ПАТЕРН FLYWEIGHT (МАПА ВІДПРАВЛЕНЬ) ===");

        LogisticsMap map = new LogisticsMap();
        String[][] types = {
                {"Звичайне", "box_icon.png", "Сірий"},
                {"Крихке", "glass_icon.png", "Червоний"},
                {"Холодове", "snowflake_icon.png", "Синій"},
                {"Документи", "doc_icon.png", "Жовтий"},
                {"Негабаритне", "pallet_icon.png", "Помаранчевий"}
        };
        String[] statuses = {"У дорозі", "Прибуло на термінал", "Видано кур'єру"};
        Random random = new Random(42);

        ConsoleLogger.log("Runner", "Генерація 20 000 позначок відправлень на мапі...");
        for (int i = 1; i <= 20_000; i++) {
            String[] selectedType = types[random.nextInt(types.length)];
            String waybill = String.format("WB-%06d", i);
            double lat = 48.0 + random.nextDouble() * 3.0;
            double lon = 24.0 + random.nextDouble() * 10.0;
            String status = statuses[random.nextInt(statuses.length)];

            map.addShipment(waybill, lat, lon, status, selectedType[0], selectedType[1], selectedType[2]);
        }

        ConsoleLogger.log("Runner", "Усього створено об'єктів позначок (ShipmentMarker): " + map.getTotalMarkersCount());
        ConsoleLogger.log("Runner", "Усього створено об'єктів типів у пам'яті (ShipmentType): " + ShipmentTypeFactory.getCreatedTypesCount());

        // Перевірка, що дві різні позначки одного типу посилаються на один і той самий об'єкт
        List<ShipmentMarker> fragileMarkers = map.getMarkersByType("Крихке");
        ShipmentMarker first = fragileMarkers.get(0);
        ShipmentMarker second = fragileMarkers.get(1);

        ConsoleLogger.log("Runner", "Порівняння двох позначок типу 'Крихке':");
        System.out.println("   1) " + first.getFullDescription());
        System.out.println("   2) " + second.getFullDescription());
        boolean sameReference = (first.getSharedType() == second.getSharedType());
        ConsoleLogger.log("Runner", "Посилання на об'єкт ShipmentType ідентичні (first.getSharedType() == second.getSharedType()): " + sameReference);
    }

    private static void runTask3Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 3: ПАТЕРН COMPOSITE (КОНСОЛІДАЦІЯ ВАНТАЖУ) ===");

        // Окремі відправлення (Листки)
        Parcel p1 = new Parcel("WB-101", "Ноутбук", 2.5, 35000.0);
        Parcel p2 = new Parcel("WB-102", "Книги", 4.0, 1200.0);
        Parcel p3 = new Parcel("WB-103", "Кавоварка", 6.2, 8500.0);
        Parcel p4 = new Parcel("WB-104", "Документи", 0.3, 500.0);
        Parcel p5 = new Parcel("WB-105", "Автозапчастини", 18.0, 6400.0);

        // Вкладений мішок (мішок у мішку!)
        CargoContainer innerBag = new CargoContainer("Мішок (цінний)", "B-102-SUB");
        innerBag.add(p1);
        innerBag.add(p4);

        // Основний мішок 1 (містить посилку та інший мішок)
        CargoContainer bag1 = new CargoContainer("Мішок", "B-101");
        bag1.add(p2);
        bag1.add(innerBag);

        // Основний мішок 2
        CargoContainer bag2 = new CargoContainer("Мішок", "B-103");
        bag2.add(p3);

        // Палета 1
        CargoContainer pallet1 = new CargoContainer("Палета", "P-01");
        pallet1.add(bag1);
        pallet1.add(bag2);

        // Палета 2 (з великогабаритною посилкою напряму)
        CargoContainer pallet2 = new CargoContainer("Палета", "P-02");
        pallet2.add(p5);

        // Вантажний рейс (Корінь дерева)
        CargoContainer freightTrip = new CargoContainer("Вантажний рейс", "TR-777 (Київ - Чернівці)");
        freightTrip.add(pallet1);
        freightTrip.add(pallet2);

        ConsoleLogger.log("Runner", "Виведення повної деревоподібної структури вантажу:");
        freightTrip.printTree("");

        System.out.println();
        ConsoleLogger.log("Runner", "Розрахунок показників тим самим викликом для різних рівнів ієрархії:");
        printNodeMetrics(freightTrip);
        printNodeMetrics(pallet1);
        printNodeMetrics(innerBag);
    }

    // Універсальний метод, який однаково працює з листом, мішком, палетою чи цілим рейсом
    private static void printNodeMetrics(CargoComponent component) {
        ConsoleLogger.log("CargoAnalytics", String.format("Вузол [%s] -> Вага: %.2f кг | Вартість: %.2f грн | Кількість відправлень: %d",
                component.getName(), component.getWeight(), component.getDeclaredValue(), component.getShipmentCount()));
    }
}