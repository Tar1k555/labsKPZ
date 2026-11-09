package com.example;

import com.example.task1_facade.DeliveryFacade;
import com.example.task3_proxy.CachedTrackingProxy;
import com.example.task3_proxy.RealPartnerTrackingService;
import com.example.task3_proxy.TrackingService;
import com.example.util.ConsoleLogger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №11. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");

        runTask1Demo();
        System.out.println("-----------------------------------------------------\n");
        runTask3Demo();
    }

    private static void runTask1Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 1: ПАТЕРН FACADE ===");
        DeliveryFacade facade = new DeliveryFacade();

        // 1. Оформлення у відділенні через Фасад
        ConsoleLogger.log("Runner", "--- 1. Оформлення відправлення у відділенні №1 ---");
        facade.shipPackage("Петренко І.", "Коваленко О.", "+380501112233", "м. Київ, вул. Хрещатик, 1", 2.5, 520.0);

        // 2. Оформлення через онлайн-кабінет тією ж операцією
        ConsoleLogger.log("Runner", "--- 2. Оформлення відправлення через Онлайн-Кабінет ---");
        facade.shipPackage("Сидоров В.", "Мельник Т.", "+380679998877", "м. Львів, вул. Франка, 10", 1.0, 610.0);

        // 3. Окремий розрахунок вартості підсистемою без повного оформлення
        ConsoleLogger.log("Runner", "--- 3. Окреме використання підсистеми розрахунку вартості ---");
        double standaloneCost = facade.getCostCalculator().calculate(10.0, 300.0);
        ConsoleLogger.log("Runner", String.format("Результат ізольованого розрахунку: %.2f грн", standaloneCost));
    }

    private static void runTask3Demo() throws InterruptedException {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 3: ПАТЕРН PROXY ===");

        RealPartnerTrackingService realService = new RealPartnerTrackingService();
        // Задаємо TTL кешу 1000 мс (1 секунда) для наочності демо
        TrackingService proxyService = new CachedTrackingProxy(realService, 1000);
        String trackingNum = "WB-990881";

        // 1. Перший запит -> Звернення до платного сервісу
        ConsoleLogger.log("Runner", "--- Крок 1: Запит статусу (Первинний) ---");
        proxyService.getStatus(trackingNum);

        // 2. Повторні 3 запити протягом TTL -> Отримання з кешу
        ConsoleLogger.log("Runner", "--- Крок 2: 3 повторні запити під час оновлення сторінки ---");
        proxyService.getStatus(trackingNum);
        proxyService.getStatus(trackingNum);
        proxyService.getStatus(trackingNum);

        // 3. Емуляція закінчення часу (затримка 1100 мс)
        ConsoleLogger.log("Runner", "--- Крок 3: Очікування спливання TTL (1.1 сек)... ---");
        Thread.sleep(1100);

        // 4. Запит після закінчення TTL -> Новий платний запит
        ConsoleLogger.log("Runner", "--- Крок 4: Запит статусу після спливання TTL ---");
        proxyService.getStatus(trackingNum);

        // Підсумок статистики
        ConsoleLogger.log("Runner", "УСЬОГО виконано платних запитів до партнера: " + ((CachedTrackingProxy) proxyService).getTotalPaidRequests());
    }
}