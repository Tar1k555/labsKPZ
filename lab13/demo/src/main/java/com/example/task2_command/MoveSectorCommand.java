package com.example.task2_command;

import com.example.util.ConsoleLogger;

public class MoveSectorCommand implements WarehouseCommand {
    private final SortingWarehouse warehouse;
    private final String trackingNumber;
    private final String newSector;
    private String previousSector;

    public MoveSectorCommand(SortingWarehouse warehouse, String trackingNumber, String newSector) {
        this.warehouse = warehouse;
        this.trackingNumber = trackingNumber;
        this.newSector = newSector;
    }

    @Override
    public void execute() {
        WarehouseParcel parcel = warehouse.getParcel(trackingNumber);
        this.previousSector = parcel.getCurrentSector();
        ConsoleLogger.log("MoveSectorCommand", "Виконання команди: перемістити " + trackingNumber + " у сектор " + newSector);
        warehouse.moveParcelToSector(trackingNumber, newSector);
    }

    @Override
    public void undo() {
        ConsoleLogger.log("MoveSectorCommand", "Скасування команди: повернення " + trackingNumber + " у попередній сектор " + previousSector);
        warehouse.moveParcelToSector(trackingNumber, previousSector);
    }

    @Override
    public String getDescription() {
        return String.format("Переміщення посилки %s у сектор '%s'", trackingNumber, newSector);
    }
}