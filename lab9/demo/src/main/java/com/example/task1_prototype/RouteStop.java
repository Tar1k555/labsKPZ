package com.example.task1_prototype;

public class RouteStop implements Cloneable {
    private String cityName;
    private String warehouseContact;

    public RouteStop(String cityName, String warehouseContact) {
        this.cityName = cityName;
        this.warehouseContact = warehouseContact;
    }

    public String getCityName() { return cityName; }

    @Override
    public RouteStop clone() {
        try {
            return (RouteStop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Помилка клонування зупинки", e);
        }
    }

    @Override
    public String toString() {
        return cityName + " (Склад: " + warehouseContact + ")";
    }
}