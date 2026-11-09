package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class PaymentProcessor {
    public boolean processPayment(String shipmentId, double amount) {
        ConsoleLogger.log("PaymentProcessor", String.format("Прийнято оплату %.2f грн за відправлення %s", amount, shipmentId));
        return true;
    }
}