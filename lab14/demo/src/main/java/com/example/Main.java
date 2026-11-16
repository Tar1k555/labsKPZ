package com.example;

import com.example.task2_mediator.*;
import com.example.util.ConsoleLogger;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №14. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");
        runTask2Demo();
    }

    private static void runTask2Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 2: ПАТЕРН MEDIATOR (СОРТУВАЛЬНИЙ ЦЕНТР) ===");

        // Ініціалізація координатора та пристроїв
        SortingCenterCoordinator coordinator = new SortingCenterCoordinator();
        ConveyorBelt belt = new ConveyorBelt("Стрічка прийому №1");
        WarehouseScale scale = new WarehouseScale("Контрольні ваги (ліміт 30 кг)", 30.0);
        BarcodeScanner oldScanner = new BarcodeScanner("Оптичний сканер Honeywell");
        DirectionSorter sorter = new DirectionSorter("Сортувальник напрямків");
        LoadingZone loadingZone = new LoadingZone("Зона завантаження №2");

        coordinator.setConveyorBelt(belt);
        coordinator.setScale(scale);
        coordinator.setScanner(oldScanner);
        coordinator.setSorter(sorter);
        coordinator.setLoadingZone(loadingZone);

        // 1. Посилка проходить стрічку: сканер -> координатор -> сортувальник («Хотин»)
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 1: Проходження посилки на напрямок «Хотин» ---");
        belt.feedParcel(new SortingParcel("WB-501", 4.5, "Хотин"));

        // 2. Спроба завантаження, коли машина ще не подана
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 2: Спроба завантаження мішка без поданої машини ---");
        loadingZone.requestBagLoading("МІШОК-ХОТИН-01");

        // 3. Подача машини -> координатор дозволяє завантаження
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 3: Подача машини на рампу ---");
        loadingZone.dockVehicle("Mercedes Atego CE1122BB");

        // 4. Ваги фіксують перевантаження -> координатор зупиняє стрічку
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 4: Перевантаження на вагах -> зупинка стрічки ---");
        belt.feedParcel(new SortingParcel("WB-502", 48.0, "Чернівці"));

        // 5. Демонстрація заміни сканера без зміни жодного іншого пристрою
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 5: Заміна сканера на нову модель без зміни інших класів ---");
        BarcodeScanner newScanner = new BarcodeScanner("Лазерний 2D-сканер Zebra DS3608");
        coordinator.setScanner(newScanner);
        belt.start();
        belt.feedParcel(new SortingParcel("WB-503", 2.1, "Хотин"));
    }
}