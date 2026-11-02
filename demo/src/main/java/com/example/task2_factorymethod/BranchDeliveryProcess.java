package com.example.task2_factorymethod;

public class BranchDeliveryProcess extends DeliveryProcess {
    @Override
    protected Delivery createDelivery() {
        return new BranchDelivery();
    }
}