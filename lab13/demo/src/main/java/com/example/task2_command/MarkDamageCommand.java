package com.example.task2_command;

import com.example.util.ConsoleLogger;

public class MarkDamageCommand implements WarehouseCommand {
    private final SortingWarehouse warehouse;
    private final String trackingNumber;
    private final String damageReason;
    private boolean previousDamagedState;
    private String previousDamageNote;

    public MarkDamageCommand(SortingWarehouse warehouse, String trackingNumber, String damageReason) {
        this.warehouse = warehouse;
        this.trackingNumber = trackingNumber;
        this.damageReason = damageReason;
    }

    @Override
    public void execute() {
        WarehouseParcel parcel = warehouse.getParcel(trackingNumber);
        this.previousDamagedState = parcel.isDamaged();
        this.previousDamageNote = parcel.getDamageNote();
        ConsoleLogger.log("MarkDamageCommand", "Виконання команди: зафіксувати пошкодження для " + trackingNumber);
        warehouse.setParcelDamage(trackingNumber, true, damageReason);
    }

    @Override
    public void undo() {
        ConsoleLogger.log("MarkDamageCommand", "Скасування команди: зняття позначки пошкодження для " + trackingNumber);
        warehouse.setParcelDamage(trackingNumber, previousDamagedState, previousDamageNote);
    }

    @Override
    public String getDescription() {
        return String.format("Позначення пошкодження посилки %s (%s)", trackingNumber, damageReason);
    }
}