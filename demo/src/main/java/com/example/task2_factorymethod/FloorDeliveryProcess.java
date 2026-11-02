package com.example.task2_factorymethod;

public class FloorDeliveryProcess extends DeliveryProcess {
    @Override
    protected Delivery createDelivery() {
        return new FloorDelivery();
    }
}