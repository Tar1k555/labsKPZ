package com.example.task3_visitor;

public interface ShipmentVisitor {
    String getOperationName();
    void visit(Letter letter);
    void visit(Parcel parcel);
    void visit(FreightCargo cargo);
    void visit(InternationalShipment international);
}