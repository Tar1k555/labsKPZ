package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class AcceptedState implements ShipmentState {
    @Override
    public String getStateName() {
        return "Прийнято";
    }

    @Override
    public void accept(ShipmentContext shipment) {
        ConsoleLogger.log("State:Прийнято", "Посилка вже прийнята.");
    }

    @Override
    public void dispatch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Прийнято", "Посилку " + shipment.getTrackingNumber() + " завантажено в магістральне авто.");
        shipment.setState(new InTransitState());
    }

    @Override
    public void arriveAtBranch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Прийнято", "ВІДМОВА: посилка ще не виїхала зі складу прийому.");
    }

    @Override
    public void deliver(ShipmentContext shipment) {
        ConsoleLogger.log("State:Прийнято", "ВІДМОВА: вручення можливе лише у відділенні призначення.");
    }

    @Override
    public void changeAddress(ShipmentContext shipment, String newAddress) {
        shipment.setDeliveryAddress(newAddress);
        ConsoleLogger.log("State:Прийнято", "УСПІХ: адресу доставки змінено на «" + newAddress + "» перед виїздом.");
    }

    @Override
    public void reroute(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:Прийнято", "ВІДМОВА: посилка ще не в дорозі.");
    }

    @Override
    public void passStorageDays(ShipmentContext shipment, int days) {
        ConsoleLogger.log("State:Прийнято", "Посилка очікує відправки, зберігання у відділенні призначення ще не почалось.");
    }
}