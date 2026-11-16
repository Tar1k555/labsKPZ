package com.example;

import com.example.task2_command.*;
import com.example.util.ConsoleLogger;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №13. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");
        runTask2Demo();
    }

    private static void runTask2Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 2: ПАТЕРН COMMAND (ОПЕРАЦІЇ НА СКЛАДІ) ===");

        // Ініціалізація складу (Receiver) та посилок у зоні прийому
        SortingWarehouse warehouse = new SortingWarehouse();
        warehouse.registerParcel(new WarehouseParcel("WB-3001", "Зона прийому"));
        warehouse.registerParcel(new WarehouseParcel("WB-3002", "Зона прийому"));
        warehouse.registerParcel(new WarehouseParcel("WB-3003", "Зона прийому"));

        ScannerCommandInvoker scannerDispatcher = new ScannerCommandInvoker();

        // 1. Сканер надсилає 5 операцій поспіль -> ставляться в чергу
        ConsoleLogger.log("Runner", "--- Крок 1: Сканер надсилає 5 операцій поспіль у чергу ---");
        scannerDispatcher.receiveFromScanner(new MoveSectorCommand(warehouse, "WB-3001", "Сектор А (Київ)"));
        scannerDispatcher.receiveFromScanner(new MoveSectorCommand(warehouse, "WB-3002", "Сектор Б (Львів)"));
        scannerDispatcher.receiveFromScanner(new MarkDamageCommand(warehouse, "WB-3003", "Надірване зовнішнє пакування"));
        scannerDispatcher.receiveFromScanner(new MoveSectorCommand(warehouse, "WB-3003", "Сектор В (Дефектація)"));
        scannerDispatcher.receiveFromScanner(new LoadVehicleCommand(warehouse, "WB-3002", "Mercedes Sprinter CE7788AA"));

        System.out.println();
        // Послідовне виконання з черги по одній
        scannerDispatcher.processAllQueuedCommands();

        // 2. Скасування помилкового завантаження посилки WB-3002 -> повертається в Сектор Б
        System.out.println();
        ConsoleLogger.log("Runner", "--- Крок 2: Скасування помилкового завантаження посилки WB-3002 ---");
        scannerDispatcher.undoLastOperation();
        ConsoleLogger.log("Runner", "Перевірка поточного стану WB-3002: " + warehouse.getParcel("WB-3002"));

        // 3. Виведення журналу операцій складу
        scannerDispatcher.printWarehouseLog();
    }
}