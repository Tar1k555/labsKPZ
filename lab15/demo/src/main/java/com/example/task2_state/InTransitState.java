package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class InTransitState implements ShipmentState {
    @Override
    public String getStateName() {
        return "В дорозі";
    }

    @Override
    public void accept(ShipmentContext shipment) {
        ConsoleLogger.log("State:В дорозі", "ВІДМОВА: посилка вже прийнята і прямує маршрутом.");
    }

    @Override
    public void dispatch(ShipmentContext shipment) {
        ConsoleLogger.log("State:В дорозі", "Посилка вже перебуває в дорозі.");
    }

    @Override
    public void arriveAtBranch(ShipmentContext shipment) {
        ConsoleLogger.log("State:В дорозі", "Посилка " + shipment.getTrackingNumber() + " прибула у відділення призначення («" + shipment.getDeliveryAddress() + "»).");
        shipment.setState(new AtBranchState());
    }

    @Override
    public void deliver(ShipmentContext shipment) {
        ConsoleLogger.log("State:В дорозі", "ВІДМОВА: неможливо вручити посилку, поки вона в дорозі.");
    }

    @Override
    public void changeAddress(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:В дорозі", "ВІДМОВА: змінити адресу напряму неможливо, оскільки відправлення вже в дорозі!");
    }

    @Override
    public void reroute(ShipmentContext shipment, String newAddress) {
        shipment.setDeliveryAddress(newAddress);
        ConsoleLogger.log("State:В дорозі", "УСПІХ: оформлено переадресацію в дорозі на нову адресу «" + newAddress + "».");
    }

    @Override
    public void passStorageDays(ShipmentContext shipment, int days) {
        ConsoleLogger.log("State:В дорозі", "Посилка в дорозі, термін зберігання у відділенні не нараховується.");
    }
}