package com.example.task2_factorymethod;

public class ParcelLockerDeliveryProcess extends DeliveryProcess {
    @Override
    protected Delivery createDelivery() {
        return new ParcelLockerDelivery();
    }
}