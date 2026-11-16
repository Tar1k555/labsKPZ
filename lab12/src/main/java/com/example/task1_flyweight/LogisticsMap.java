package com.example.task1_flyweight;

import java.util.ArrayList;
import java.util.List;

public class LogisticsMap {
    private final List<ShipmentMarker> markers = new ArrayList<>();

    public void addShipment(String waybill, double lat, double lon, String status,
                            String typeName, String icon, String color) {
        ShipmentType type = ShipmentTypeFactory.getShipmentType(typeName, icon, color);
        markers.add(new ShipmentMarker(waybill, lat, lon, status, type));
    }

    public int getTotalMarkersCount() {
        return markers.size();
    }

    public List<ShipmentMarker> getMarkersByType(String typeName) {
        List<ShipmentMarker> result = new ArrayList<>();
        for (ShipmentMarker m : markers) {
            if (m.getSharedType().getTypeName().equals(typeName)) {
                result.add(m);
            }
        }
        return result;
    }
}