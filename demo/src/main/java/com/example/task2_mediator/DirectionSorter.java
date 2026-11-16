package com.example.task2_mediator;

import com.example.util.ConsoleLogger;

public class DirectionSorter extends SortingDevice {
    public DirectionSorter(String deviceName) {
        super(deviceName);
    }

    public void routeParcel(SortingParcel parcel) {
        ConsoleLogger.log(deviceName, "РЕАКЦІЯ: сортувальник направив посилку " + parcel.getTrackingNumber()
                + " у рукав напрямку «" + parcel.getDestination() + "»");
        mediator.notify(this, "SORTED", parcel);
    }
}