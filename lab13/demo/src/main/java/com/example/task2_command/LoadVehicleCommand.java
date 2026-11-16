package com.example.task2_command;

import com.example.util.ConsoleLogger;

public class LoadVehicleCommand implements WarehouseCommand {
    private final SortingWarehouse warehouse;
    private final String trackingNumber;
    private final String vehiclePlate;
    private String previousSector;

    public LoadVehicleCommand(SortingWarehouse warehouse, String trackingNumber, String vehiclePlate) {
        this.warehouse = warehouse;
        this.trackingNumber = trackingNumber;
        this.vehiclePlate = vehiclePlate;
    }

    @Override
    public void execute() {
        WarehouseParcel parcel = warehouse.getParcel(trackingNumber);
        this.previousSector = parcel.getCurrentSector();
        ConsoleLogger.log("LoadVehicleCommand", "Виконання команди: завантажити " + trackingNumber + " в авто " + vehiclePlate);
        warehouse.loadParcelIntoVehicle(trackingNumber, vehiclePlate);
    }

    @Override
    public void undo() {
        ConsoleLogger.log("LoadVehicleCommand", "Скасування помилкового завантаження: вивантаження " + trackingNumber + " назад у сектор " + previousSector);
        warehouse.unloadParcelToSector(trackingNumber, previousSector);
    }

    @Override
    public String getDescription() {
        return String.format("Завантаження посилки %s в авто '%s'", trackingNumber, vehiclePlate);
    }
}