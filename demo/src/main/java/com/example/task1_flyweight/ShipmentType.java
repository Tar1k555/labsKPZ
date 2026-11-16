package com.example.task1_flyweight;

public class ShipmentType {
    private final String typeName;
    private final String icon;
    private final String color;

    public ShipmentType(String typeName, String icon, String color) {
        this.typeName = typeName;
        this.icon = icon;
        this.color = color;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getIcon() {
        return icon;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Тип='%s' [іконка=%s, колір=%s, hash=%d]",
                typeName, icon, color, System.identityHashCode(this));
    }
}