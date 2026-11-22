package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class DeliveredState implements ShipmentState {
    @Override
    public String getStateName() {
        return "Вручено";
    }

    @Override
    public void accept(ShipmentContext shipment) {
        ConsoleLogger.log("State:Вручено", "ВІДМОВА: посилку вже вручено клієнту.");
    }

    @Override
    public void dispatch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Вручено", "ВІДМОВА: посилку вже вручено клієнту.");
    }

    @Override
    public void arriveAtBranch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Вручено", "ВІДМОВА: посилку вже вручено клієнту.");
    }

    @Override
    public void deliver(ShipmentContext shipment) {
        ConsoleLogger.log("State:Вручено", "Посилка вже була вручена раніше.");
    }

    @Override
    public void changeAddress(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:Вручено", "ВІДМОВА: неможливо змінити адресу врученої посилки.");
    }

    @Override
    public void reroute(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:Вручено", "ВІДМОВА: неможливо переадресувати вручену посилку.");
    }

    @Override
    public void passStorageDays(ShipmentContext shipment, int days) {
        ConsoleLogger.log("State:Вручено", "Посилка вже вручена, зберігання завершено.");
    }
}