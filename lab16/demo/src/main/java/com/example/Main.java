package com.example;

import com.example.task3_visitor.*;
import com.example.util.ConsoleLogger;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА №16. ВАРІАНТ 3 («ПОСИЛКА+») ===\n");
        runTask3Demo();
    }

    private static void runTask3Demo() {
        ConsoleLogger.log("Runner", "=== ЗАВДАННЯ 3: ПАТЕРН VISITOR (ОПЕРАЦІЇ НАД ВАНТАЖЕМ) ===");

        ShipmentBatch batch = new ShipmentBatch("Партія відправлень №2026-11");
        batch.addShipment(new Letter("LT-101", 320.0, true));
        batch.addShipment(new Parcel("PR-202", 450.0, 5.5, 2400.0));
        batch.addShipment(new FreightCargo("FR-303", 520.0, 340.0, 2));
        batch.addShipment(new InternationalShipment("IN-404", 1450.0, 8.2, "Польща", "8517.13.00", 350.0));

        // Початковий стан об'єктів до виконання операцій
        batch.printStates("ДО виконання операцій");

        // 1. Розрахунок вартості доставки одним проходом
        System.out.println();
        DeliveryCostVisitor costVisitor = new DeliveryCostVisitor();
        batch.acceptVisitor(costVisitor);
        ConsoleLogger.log("Runner", String.format("Підсумок операції: загальна вартість партії = %.2f грн", costVisitor.getTotalBatchCost()));
        batch.printStates("ПІСЛЯ розрахунку вартості");

        // 2. Формування митної декларації для того самого набору
        System.out.println();
        CustomsDeclarationVisitor customsVisitor = new CustomsDeclarationVisitor();
        batch.acceptVisitor(customsVisitor);
        ConsoleLogger.log("Runner", "Підсумок операції: оформлено митних декларацій = " + customsVisitor.getDeclarationsCount());
        batch.printStates("ПІСЛЯ митного оформлення");

        // 3. Додавання операції «вуглецевий слід» одним класом без зміни класів відправлень
        System.out.println();
        CarbonFootprintVisitor carbonVisitor = new CarbonFootprintVisitor();
        batch.acceptVisitor(carbonVisitor);
        ConsoleLogger.log("Runner", String.format("Підсумок операції: сумарний вуглецевий слід = %.2f кг CO2", carbonVisitor.getTotalCo2Kg()));
        batch.printStates("ПІСЛЯ розрахунку вуглецевого сліду");
    }
}