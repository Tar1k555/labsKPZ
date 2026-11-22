package com.example.task3_visitor;

import com.example.util.ConsoleLogger;
import java.util.ArrayList;
import java.util.List;

public class ShipmentBatch {
    private final String batchName;
    private final List<ShipmentElement> shipments = new ArrayList<>();

    public ShipmentBatch(String batchName) {
        this.batchName = batchName;
    }

    public void addShipment(ShipmentElement shipment) {
        shipments.add(shipment);
    }

    public void printStates(String stageLabel) {
        ConsoleLogger.log("ShipmentBatch", "Стан об'єктів (" + stageLabel + "):");
        for (ShipmentElement element : shipments) {
            System.out.println("   • " + element.getStateSummary());
        }
    }

    public void acceptVisitor(ShipmentVisitor visitor) {
        ConsoleLogger.log("ShipmentBatch", ">>> Запуск операції [" + visitor.getOperationName() + "] над набором '" + batchName + "' одним проходом:");
        for (ShipmentElement element : shipments) {
            element.accept(visitor);
        }
    }
}