package com.example.task1_flyweight;

import com.example.util.ConsoleLogger;
import java.util.HashMap;
import java.util.Map;

public class ShipmentTypeFactory {
    private static final Map<String, ShipmentType> cache = new HashMap<>();

    public static ShipmentType getShipmentType(String typeName, String icon, String color) {
        ShipmentType type = cache.get(typeName);
        if (type == null) {
            type = new ShipmentType(typeName, icon, color);
            cache.put(typeName, type);
            ConsoleLogger.log("ShipmentTypeFactory", "Створено НОВИЙ об'єкт-легковаговик у пам'яті: " + type);
        }
        return type;
    }

    public static int getCreatedTypesCount() {
        return cache.size();
    }
}