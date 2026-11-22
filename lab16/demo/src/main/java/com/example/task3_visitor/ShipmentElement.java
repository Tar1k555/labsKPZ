package com.example.task3_visitor;

public interface ShipmentElement {
    void accept(ShipmentVisitor visitor);
    String getStateSummary();
}