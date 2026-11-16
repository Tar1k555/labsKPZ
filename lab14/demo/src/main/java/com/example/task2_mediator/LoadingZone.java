package com.example.task2_mediator;

import com.example.util.ConsoleLogger;

public class LoadingZone extends SortingDevice {
    private boolean vehicleDocked = false;
    private String vehiclePlate = null;

    public LoadingZone(String deviceName) {
        super(deviceName);
    }

    public void requestBagLoading(String bagCode) {
        ConsoleLogger.log(deviceName, "ІНІЦІАТОР: запит на завантаження мішка [" + bagCode + "] на рампу.");
        mediator.notify(this, "REQUEST_LOADING", bagCode);
    }

    public void dockVehicle(String plate) {
        this.vehicleDocked = true;
        this.vehiclePlate = plate;
        ConsoleLogger.log(deviceName, "ІНІЦІАТОР: машину [" + plate + "] подано на рампу завантаження.");
        mediator.notify(this, "VEHICLE_DOCKED", plate);
    }

    public boolean isVehicleDocked() {
        return vehicleDocked;
    }

    public void allowLoading(String bagCode) {
        ConsoleLogger.log(deviceName, "РЕАКЦІЯ: розпочато завантаження мішка [" + bagCode + "] у машину [" + vehiclePlate + "].");
    }

    public void denyLoading(String bagCode) {
        ConsoleLogger.log(deviceName, "РЕАКЦІЯ: завантаження мішка [" + bagCode + "] ЗАБЛОКОВАНО (машина ще не подана).");
    }
}