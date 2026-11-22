package com.example.task2_state;

import com.example.util.ConsoleLogger;

public class CreatedState implements ShipmentState {
    @Override
    public String getStateName() {
        return "Створено";
    }

    @Override
    public void accept(ShipmentContext shipment) {
        ConsoleLogger.log("State:Створено", "Посилку " + shipment.getTrackingNumber() + " прийнято оператором у відділенні.");
        shipment.setState(new AcceptedState());
    }

    @Override
    public void dispatch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Створено", "ВІДМОВА: неможливо відправити в дорогу неприйняту посилку " + shipment.getTrackingNumber());
    }

    @Override
    public void arriveAtBranch(ShipmentContext shipment) {
        ConsoleLogger.log("State:Створено", "ВІДМОВА: посилка ще не відправлена.");
    }

    @Override
    public void deliver(ShipmentContext shipment) {
        ConsoleLogger.log("State:Створено", "ВІДМОВА: неможливо вручити посилку зі стану 'Створено'.");
    }

    @Override
    public void changeAddress(ShipmentContext shipment, String newAddress) {
        shipment.setDeliveryAddress(newAddress);
        ConsoleLogger.log("State:Створено", "УСПІХ: адресу доставки змінено на «" + newAddress + "» до моменту відправлення.");
    }

    @Override
    public void reroute(ShipmentContext shipment, String newAddress) {
        ConsoleLogger.log("State:Створено", "ВІДМОВА: переадресація доступна лише в дорозі; скористайтеся зміною адреси.");
    }

    @Override
    public void passStorageDays(ShipmentContext shipment, int days) {
        ConsoleLogger.log("State:Створено", "Лічильник зберігання у відділенні не діє у стані 'Створено'.");
    }
}