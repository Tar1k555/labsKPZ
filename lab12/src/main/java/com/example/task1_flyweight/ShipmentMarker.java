package com.example.task1_flyweight;

public class ShipmentMarker {
    private final String waybillNumber;
    private final double latitude;
    private final double longitude;
    private final String status;
    private final ShipmentType sharedType; // Посилання на спільний Flyweight

    public ShipmentMarker(String waybillNumber, double latitude, double longitude, String status, ShipmentType sharedType) {
        this.waybillNumber = waybillNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.sharedType = sharedType;
    }

    public ShipmentType getSharedType() {
        return sharedType;
    }

    public String getFullDescription() {
        return String.format("Позначка [Накладна=%s, Координати=(%.4f, %.4f), Статус='%s'] -> %s",
                waybillNumber, latitude, longitude, status, sharedType.toString());
    }
}