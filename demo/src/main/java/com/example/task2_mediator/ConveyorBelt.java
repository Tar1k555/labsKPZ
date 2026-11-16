package com.example.task2_mediator;

import com.example.util.ConsoleLogger;

public class ConveyorBelt extends SortingDevice {
    private boolean running = true;

    public ConveyorBelt(String deviceName) {
        super(deviceName);
    }

    public void feedParcel(SortingParcel parcel) {
        if (!running) {
            ConsoleLogger.log(deviceName, "Відмова: стрічка зупинена, подача посилки " + parcel.getTrackingNumber() + " неможлива.");
            return;
        }
        ConsoleLogger.log(deviceName, "ІНІЦІАТОР: на стрічку надійшла посилка " + parcel);
        mediator.notify(this, "PARCEL_ON_BELT", parcel);
    }

    public void stop() {
        this.running = false;
        ConsoleLogger.log(deviceName, "РЕАКЦІЯ: стрічку прийому екстрено ЗУПИНЕНО.");
    }

    public void start() {
        this.running = true;
        ConsoleLogger.log(deviceName, "Стрічку прийому ЗАПУЩЕНО в роботу.");
    }

    public boolean isRunning() {
        return running;
    }
}