package com.example.task2_command;

public class WarehouseParcel {
    private final String trackingNumber;
    private String currentSector;
    private String assignedVehicle;
    private boolean damaged;
    private String damageNote;

    public WarehouseParcel(String trackingNumber, String initialSector) {
        this.trackingNumber = trackingNumber;
        this.currentSector = initialSector;
        this.assignedVehicle = null;
        this.damaged = false;
        this.damageNote = "";
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getCurrentSector() {
        return currentSector;
    }

    public void setCurrentSector(String currentSector) {
        this.currentSector = currentSector;
    }

    public String getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(String assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged, String damageNote) {
        this.damaged = damaged;
        this.damageNote = damageNote;
    }

    public String getDamageNote() {
        return damageNote;
    }

    @Override
    public String toString() {
        String location = (assignedVehicle != null)
                ? "Авто [" + assignedVehicle + "]"
                : "Сектор [" + currentSector + "]";
        String condition = damaged ? "ПОШКОДЖЕНО (" + damageNote + ")" : "Ціла";
        return String.format("Посилка %s | Місце: %s | Стан: %s", trackingNumber, location, condition);
    }
}