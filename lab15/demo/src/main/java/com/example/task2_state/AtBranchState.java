package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class AtBranchState implements ShipmentState {
    private static final int MAX_STORAGE_DAYS = 5;

    @Override
    public String getStateName() {
        return "У відділенні";
    }

    @Override
    public void accept(ShipmentContext shipment) {
        ConsoleLogger.log("State:У відділенні", "Посилка вже у відділенні призначення.");
    }

    @Override
    public void dispatch(ShipmentContext shipment) {
        ConsoleLogger.log("State:У відділенні", "ВІДМОВА: посилка вже доставлена у відділення.");
    }

    @Override
    public void arriveAtBranch(ShipmentContext shipment) {
        ConsoleLogger.log("State:У відділенні", "Посилка вже перебуває у відділенні.");
    }

    @Override
    public void deliver(ShipmentContext shipment) {
        ConsoleLogger.log("State:У відділенні", "Посилку " + shipment.getTrackingNumber() + " видано отримувачу за адресою «" + shipment.getDeliveryAddress() + "».");
        shipment.setState(new DeliveredState());
    }

    @Override
    public void changeAddress(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:У відділенні", "ВІДМОВА: звичайна зміна адреси недоступна у відділенні.");
    }

    @Override
    public void reroute(ShipmentContext shipment, String newAddress) {
        shipment.setDeliveryAddress(newAddress);
        ConsoleLogger.log("State:У відділенні", "Оформлено переадресацію зі складу відділення на «" + newAddress + "».");
        shipment.setState(new InTransitState());
    }

    @Override
    public void passStorageDays(ShipmentContext shipment, int days) {
        shipment.addDaysAtBranch(days);
        ConsoleLogger.log("State:У відділенні", "Посилка " + shipment.getTrackingNumber() + " зберігається у відділенні вже "
                + shipment.getDaysAtBranch() + " дн. (ліміт: " + MAX_STORAGE_DAYS + " дн.).");
        if (shipment.getDaysAtBranch() >= MAX_STORAGE_DAYS) {
            ConsoleLogger.log("State:У відділенні", "Термін зберігання (5 днів) вичерпано! Ініційовано повернення відправнику.");
            shipment.setState(new ReturnedToSenderState());
        }
    }
}