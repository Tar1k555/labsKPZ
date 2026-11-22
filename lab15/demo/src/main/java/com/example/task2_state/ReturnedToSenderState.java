package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class ReturnedToSenderState implements ShipmentState {
    @Override
    public String getStateName() {
        return "Повернення відправнику";
    }

    @Override
    public void accept(ShipmentContext shipment) {
        ConsoleLogger.log("State:Повернення", "ВІДМОВА: відправлення оформлено на повернення.");
    }

    @Override
    public void dispatch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Повернення", "Посилка прямує зворотним рейсом до відправника.");
    }

    @Override
    public void arriveAtBranch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Повернення", "Повернення прибуло у відділення відправника.");
    }

    @Override
    public void deliver(ShipmentContext shipment) {
        ConsoleLogger.log("State:Повернення", "ВІДМОВА: видача отримувачу заблокована через закінчення строку зберігання.");
    }

    @Override
    public void changeAddress(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:Повернення", "ВІДМОВА: посилка в процесі повернення відправнику.");
    }

    @Override
    public void reroute(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:Повернення", "ВІДМОВА: переадресація повернення неможлива.");
    }

    @Override
    public void passStorageDays(ShipmentContext shipment, int days) {
        ConsoleLogger.log("State:Повернення", "Посилка вже переведена у статус повернення.");
    }
}