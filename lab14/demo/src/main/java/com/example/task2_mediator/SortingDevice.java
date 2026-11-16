package com.example.task2_mediator;

public abstract class SortingDevice {
    protected SortingCenterMediator mediator;
    protected final String deviceName;

    public SortingDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setMediator(SortingCenterMediator mediator) {
        this.mediator = mediator;
    }

    public String getDeviceName() {
        return deviceName;
    }
}