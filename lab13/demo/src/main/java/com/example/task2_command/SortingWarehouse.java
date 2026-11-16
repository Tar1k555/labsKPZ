package com.example.task2_command;

import com.example.util.ConsoleLogger;
import java.util.HashMap;
import java.util.Map;

public class SortingWarehouse {
    private final Map<String, WarehouseParcel> parcels = new HashMap<>();

    public void registerParcel(WarehouseParcel parcel) {
        parcels.put(parcel.getTrackingNumber(), parcel);
    }

    public WarehouseParcel getParcel(String trackingNumber) {
        WarehouseParcel parcel = parcels.get(trackingNumber);
        if (parcel == null) {
            throw new IllegalArgumentException("Посилку " + trackingNumber + " не знайдено на складі!");
        }
        return parcel;
    }

    public void moveParcelToSector(String trackingNumber, String targetSector) {
        WarehouseParcel parcel = getParcel(trackingNumber);
        parcel.setAssignedVehicle(null);
        parcel.setCurrentSector(targetSector);
        ConsoleLogger.log("SortingWarehouse", "Фактично виконано переміщення: " + parcel);
    }

    public void loadParcelIntoVehicle(String trackingNumber, String vehiclePlate) {
        WarehouseParcel parcel = getParcel(trackingNumber);
        parcel.setCurrentSector("В дорозі / Завантажено");
        parcel.setAssignedVehicle(vehiclePlate);
        ConsoleLogger.log("SortingWarehouse", "Фактично виконано завантаження в авто: " + parcel);
    }

    public void unloadParcelToSector(String trackingNumber, String returnSector) {
        WarehouseParcel parcel = getParcel(trackingNumber);
        parcel.setAssignedVehicle(null);
        parcel.setCurrentSector(returnSector);
        ConsoleLogger.log("SortingWarehouse", "Фактично виконано повернення з авто в сектор: " + parcel);
    }

    public void setParcelDamage(String trackingNumber, boolean damaged, String note) {
        WarehouseParcel parcel = getParcel(trackingNumber);
        parcel.setDamaged(damaged, note);
        ConsoleLogger.log("SortingWarehouse", "Фактично оновлено стан пошкодження: " + parcel);
    }
}