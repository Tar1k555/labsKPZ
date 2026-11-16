package com.example.task3_composite;

import java.util.ArrayList;
import java.util.List;

public class CargoContainer implements CargoComponent {
    private final String containerType; // Наприклад: "Рейс", "Палета", "Мішок"
    private final String code;
    private final List<CargoComponent> children = new ArrayList<>();

    public CargoContainer(String containerType, String code) {
        this.containerType = containerType;
        this.code = code;
    }

    public void add(CargoComponent component) {
        children.add(component);
    }

    public void remove(CargoComponent component) {
        children.remove(component);
    }

    @Override
    public String getName() {
        return containerType + " №" + code;
    }

    @Override
    public double getWeight() {
        double totalWeight = 0;
        for (CargoComponent child : children) {
            totalWeight += child.getWeight();
        }
        return totalWeight;
    }

    @Override
    public double getDeclaredValue() {
        double totalValue = 0;
        for (CargoComponent child : children) {
            totalValue += child.getDeclaredValue();
        }
        return totalValue;
    }

    @Override
    public int getShipmentCount() {
        int count = 0;
        for (CargoComponent child : children) {
            count += child.getShipmentCount();
        }
        return count;
    }

    @Override
    public void printTree(String indent) {
        System.out.printf("%s[+] %s [Загальна вага: %.2f кг | Вартість: %.2f грн | Відправлень: %d шт.]%n",
                indent, getName(), getWeight(), getDeclaredValue(), getShipmentCount());
        for (CargoComponent child : children) {
            child.printTree(indent + "    ");
        }
    }
}