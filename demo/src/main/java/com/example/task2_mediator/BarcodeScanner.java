package com.example.task2_mediator;

import com.example.util.ConsoleLogger;

public class BarcodeScanner extends SortingDevice {
    public BarcodeScanner(String deviceName) {
        super(deviceName);
    }

    public void scan(SortingParcel parcel) {
        ConsoleLogger.log(deviceName, "ІНІЦІАТОР: зчитано штрихкод посилки " + parcel.getTrackingNumber()
                + ", визначено напрямок «" + parcel.getDestination() + "»");
        mediator.notify(this, "SCANNED", parcel);
    }
}