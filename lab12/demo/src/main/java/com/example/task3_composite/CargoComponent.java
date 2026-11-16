package com.example.task3_composite;

public interface CargoComponent {
    String getName();
    double getWeight();
    double getDeclaredValue();
    int getShipmentCount();
    void printTree(String indent);
}