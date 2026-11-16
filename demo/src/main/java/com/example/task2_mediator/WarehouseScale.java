package com.example.task2_mediator;

import com.example.util.ConsoleLogger;

public class WarehouseScale extends SortingDevice {
    private final double maxWeightKg;

    public WarehouseScale(String deviceName, double maxWeightKg) {
        super(deviceName);
        this.maxWeightKg = maxWeightKg;
    }

    public void weigh(SortingParcel parcel) {
        if (parcel.getWeightKg() > maxWeightKg) {
            ConsoleLogger.log(deviceName, String.format("ІНІЦІАТОР: зафіксовано ПЕРЕВАНТАЖЕННЯ для %s (%.1f кг > ліміт %.1f кг)!",
                    parcel.getTrackingNumber(), parcel.getWeightKg(), maxWeightKg));
            mediator.notify(this, "OVERWEIGHT", parcel);
        } else {
            ConsoleLogger.log(deviceName, String.format("РЕАКЦІЯ/ІНІЦІАТОР: зважено %s — вага %.1f кг у межах норми.",
                    parcel.getTrackingNumber(), parcel.getWeightKg()));
            mediator.notify(this, "WEIGHT_OK", parcel);
        }
    }
}