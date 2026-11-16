package com.example.task2_factorymethod;

public class CourierDeliveryProcess extends DeliveryProcess {
    @Override
    protected Delivery createDelivery() {
        return new CourierDelivery();
    }
}